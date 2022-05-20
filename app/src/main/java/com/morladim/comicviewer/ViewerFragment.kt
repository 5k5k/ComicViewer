package com.morladim.comicviewer

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.morladim.comicviewer.common.ui.BaseFragment
import com.morladim.comicviewer.ui.ImageAdapter
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/20 at 8:45
 */
class ViewerFragment : BaseFragment(R.layout.fragment_viewer) {

    private var imageRecycler: RecyclerView? = null

    private var imageAdapter: ImageAdapter? = null

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        imageRecycler = view.findViewById(R.id.imageRecycler)
        imageAdapter = ImageAdapter()
        imageRecycler?.adapter = imageAdapter

        val images:List<File> = arguments?.get("images") as List<File>
        imageAdapter?.list = images
        imageAdapter?.notifyDataSetChanged()
    }
}