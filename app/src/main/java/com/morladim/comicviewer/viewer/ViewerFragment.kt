package com.morladim.comicviewer.viewer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morladim.comicviewer.R
import com.morladim.comicviewer.common.Constants.viewerFragmentArgumentKey
import com.morladim.comicviewer.common.Constants.viewerFragmentArgumentPathKey
import com.morladim.comicviewer.common.LogUtils
import com.morladim.comicviewer.common.ui.BaseBindingFragment
import com.morladim.comicviewer.databinding.FragmentViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/20
 */
@AndroidEntryPoint
class ViewerFragment : BaseBindingFragment<FragmentViewerBinding>(R.layout.fragment_viewer) {

    private var imageAdapter: ImageAdapter = ImageAdapter()
    override val enableCachedView = false
    private val viewerViewModel by viewModels<ViewerViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        binding!!.imageRecycler.adapter = imageAdapter
        arguments?.let {
            if (it.containsKey(viewerFragmentArgumentKey)) {
                val images: List<File> = it.get(viewerFragmentArgumentKey) as List<File>
                LogUtils.trace("加载图片数量：" + images.size)
                imageAdapter.data = images
                imageAdapter.notifyDataSetChanged()
            }
            if (it.containsKey(viewerFragmentArgumentPathKey)) {
                val path = it.getString(viewerFragmentArgumentPathKey)
                path?.let {
                    initRecyclerView(path)
                }
            }
        }
    }

    private fun initRecyclerView(path: String) {
        viewerViewModel.init(path)
        viewerViewModel.viewHistory.observe(viewLifecycleOwner, { history ->
            if (history != null && !viewerViewModel.hasInitScroll) {
                binding!!.imageRecycler.viewTreeObserver.addOnGlobalLayoutListener {
                    val range = binding!!.imageRecycler.computeVerticalScrollRange()
                    if (history.scroll > range || viewerViewModel.hasSetHistory()) {
                        return@addOnGlobalLayoutListener
                    }
                    viewerViewModel.setHistory()
                    val manager = binding!!.imageRecycler.layoutManager as LinearLayoutManager
                    manager.scrollToPositionWithOffset(0, -history.scroll)
                }
            }
        })

        binding!!.imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val offset = recyclerView.computeVerticalScrollOffset()
                    val extent = recyclerView.computeVerticalScrollExtent()
                    val range = recyclerView.computeVerticalScrollRange()

                    val percentage = (100f * offset / (range - extent).toFloat())

                    viewerViewModel.updateProcess(offset, percentage)
                }
            }
        })
    }
}