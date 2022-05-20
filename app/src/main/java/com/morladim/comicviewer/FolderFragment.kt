package com.morladim.comicviewer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.morladim.comicviewer.common.addVerticalDecorator
import com.morladim.comicviewer.common.subImageList
import com.morladim.comicviewer.common.subfolderList
import com.morladim.comicviewer.common.ui.BaseFragment
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/20 at 8:45
 */
class FolderFragment : BaseFragment(R.layout.fragment_folder), FolderAdapter.FolderAdapterListener {

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            requestData()
        } else {
            activity?.finish()
        }
    }
//    private var folderRecycler: RecyclerView? = null

    private var adapter: FolderAdapter? = null

    private var currentPath: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (currentPath == null || currentPath == rootPath) {
                activity?.finish()
                return@addCallback
            }
            if (this@FolderFragment.isHidden) {
                requireActivity().onBackPressed()
            } else {
                val position = currentPath!!.lastIndexOf(File.separator)
                currentPath = currentPath!!.substring(0, position)
                currentPath?.let {
                    onItemClick(File(it))
                }
            }
        }
    }

    private fun requestPermission() {
        context?.let {
            if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                requestData()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
//            when {
//                ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> requestData()
//                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
//
//                }
//                else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//            }
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        val folderRecyclerView = view.findViewById<RecyclerView>(R.id.folderRecycler)
        adapter = FolderAdapter()
        folderRecyclerView?.addVerticalDecorator()
        folderRecyclerView?.adapter = adapter
        adapter!!.listener = this
    }

    private val path = File.separator + "a"

    private val rootPath = Environment.getExternalStorageDirectory().absolutePath + path

    private fun requestData() {
        val file = File(rootPath)
        currentPath = file.absolutePath
        adapter?.setData(file.subfolderList())
        adapter?.notifyDataSetChanged()
    }

    override fun onItemClick(file: File) {
        currentPath = file.absolutePath
        adapter?.setData(file.subfolderList())
        adapter?.notifyDataSetChanged()
    }

    override fun onStartClick(file: File) {
        val bundle = bundleOf("images" to file.subImageList())
        findNavController().navigate(R.id.action_folder_to_viewer, bundle)
    }
}