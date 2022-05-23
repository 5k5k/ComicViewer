package com.morladim.comicviewer.folder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.morladim.comicviewer.R
import com.morladim.comicviewer.common.Constants.viewerFragmentArgumentKey
import com.morladim.comicviewer.common.Constants.viewerFragmentArgumentPathKey
import com.morladim.comicviewer.common.addVerticalDecorator
import com.morladim.comicviewer.common.subImageList
import com.morladim.comicviewer.common.ui.BaseBindingFragment
import com.morladim.comicviewer.databinding.FragmentFolderBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

/**
 * @author 5k5k
 * Created 2022/5/20
 */
@AndroidEntryPoint
class FolderFragment : BaseBindingFragment<FragmentFolderBinding>(R.layout.fragment_folder), FolderAdapter.FolderAdapterListener {

    private val viewModel by viewModels<FolderViewModel>()
    override val enableCachedView = false

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.init()
        } else {
            activity?.finish()
        }
    }

    private var adapter = FolderAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (viewModel.currentIsRoot()) {
                activity?.finish()
                return@addCallback
            }
            if (this@FolderFragment.isHidden) {
                requireActivity().onBackPressed()
            } else {
                viewModel.toUpFolder()
            }
        }
    }

    private fun requestPermission() {
        context?.let {
            if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                viewModel.init()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding!!.viewModel = viewModel
        binding!!.folderRecycler.addVerticalDecorator()
        binding!!.folderRecycler.adapter = adapter
        adapter.listener = this
        viewModel.currentData.observe(viewLifecycleOwner, {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(file: File) {
        viewModel.onItemClick(file)
    }

    override fun onStartClick(file: File) {
        val bundle = bundleOf(viewerFragmentArgumentKey to file.subImageList(), viewerFragmentArgumentPathKey to file.absolutePath)
        findNavController().navigate(R.id.action_folder_to_viewer, bundle)
    }
}