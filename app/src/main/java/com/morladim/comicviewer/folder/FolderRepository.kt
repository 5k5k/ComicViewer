package com.morladim.comicviewer.folder

import android.os.Environment
import com.morladim.comicviewer.common.subfolderList
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author 5k5k
 * Created 2022/5/20
 */
@Singleton
class FolderRepository @Inject constructor() {

    private val path = File.separator + "a"

    val rootPath = Environment.getExternalStorageDirectory().absolutePath + path

    fun getRoot(): List<File> {
        val file = File(rootPath)
        return file.subfolderList()
    }
}