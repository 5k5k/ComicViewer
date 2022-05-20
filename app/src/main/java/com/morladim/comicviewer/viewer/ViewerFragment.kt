package com.morladim.comicviewer.viewer

import android.os.Bundle
import android.view.View
import com.morladim.comicviewer.R
import com.morladim.comicviewer.common.Constants.viewerFragmentArgumentKey
import com.morladim.comicviewer.common.LogUtils
import com.morladim.comicviewer.common.ui.BaseBindingFragment
import com.morladim.comicviewer.databinding.FragmentViewerBinding
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/20
 */
class ViewerFragment : BaseBindingFragment<FragmentViewerBinding>(R.layout.fragment_viewer) {

    private var imageAdapter: ImageAdapter = ImageAdapter()
    override val enableCachedView = false

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
        }

    }
}