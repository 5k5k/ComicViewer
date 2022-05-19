package com.morladim.comicoviewer

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.ListPreloader.PreloadSizeProvider
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider
import com.morladim.comicoviewer.common.addVerticalDecorator
import com.morladim.comicoviewer.ui.ImageAdapter
import java.io.File


class MainActivity : AppCompatActivity(), FolderAdapter.FolderAdapterListener {

    private val path = "/a"

    private val requestCode = 1

    private val rootPath = Environment.getExternalStorageDirectory().absolutePath + path

    private var folderRecycler: RecyclerView? = null
    private var imageRecycler: RecyclerView? = null

    private var adapter: FolderAdapter? = null

    private var imageAdapter: ImageAdapter? = null

    private var currentFolderList: List<File>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        requestPermission()

    }

    private fun initView() {
        folderRecycler = findViewById(R.id.folderRecycler)
        adapter = FolderAdapter()
        folderRecycler?.addVerticalDecorator()
        folderRecycler?.adapter = adapter
        adapter!!.listener = this

        imageRecycler = findViewById(R.id.imageRecycler)
        imageAdapter = ImageAdapter()
        imageRecycler?.adapter = imageAdapter

//        val img = findViewById<ImageView>(R.id.img)
//        Glide.with(this).load("https://csdnimg.cn/release/blogv2/dist/pc/img/original.png").into(img)

//        val sizeProvider: PreloadSizeProvider<*> = FixedPreloadSizeProvider<Any?>(imageWidth, imageHeight)
//        val modelProvider: PreloadModelProvider<*> = PreloadModelProvider(glideRequest, titleDetail.getLevel(), chapterList)
//        val preloader: RecyclerViewPreloader<Drawable> = RecyclerViewPreloader<Any?>(Glide.with(this), modelProvider, sizeProvider, maxPreload)
//        recyclerView.getRecyclerView().addOnScrollListener(preloader)
    }

    private fun requestData() {
        currentFolderList = getAllSubFolder(rootPath)
        adapter?.setData(currentFolderList)
        adapter?.notifyDataSetChanged()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                requestData()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
            }
        } else {
            requestData()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.requestCode) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                requestData()
            } else {
                println("error")
            }
        }
    }

    fun getAllSubFiles(path: String): List<File>? {
        val file = File(path)
        val allFile = file.listFiles()
        return allFile?.filter { it.isFile }
    }

    fun getAllSubFiles(file: File): List<File>? {
        val allFile = file.listFiles()
        return allFile?.filter { it.isFile }
    }

    private fun getAllSubFolder(path: String): List<File>? {
        val file = File(path)
        val allFile = file.listFiles()
        return allFile?.filter { !it.isFile }
    }

    private fun getAllSubFolder(file: File): List<File>? {
        val allFile = file.listFiles()
        return allFile?.filter { !it.isFile }
    }

    override fun onItemClick(file: File) {
        currentFolderList = getAllSubFolder(file)
        adapter?.setData(currentFolderList)
        adapter?.notifyDataSetChanged()
    }

    override fun onStartClick(file: File) {
        val f = getAllSubFiles(file)?.filter { it.extension == "jpg" || it.extension == "png" }
        imageRecycler?.visibility = View.VISIBLE
        folderRecycler?.visibility = View.INVISIBLE
        imageAdapter?.list = f
        imageAdapter?.notifyDataSetChanged()

    }
}