package com.morladim.comicviewer.common

import java.io.File
import java.util.*

/**
 * @author 5k5k
 * Created 2022/5/20
 */

val imageExtensionNameArray = arrayOf("png", "jpg", "jpeg", "bmp")

fun File.subfolderList(): List<File> {
    if (this.isFile) {
        throw RuntimeException("not a folder")
    }
    val allFile = this.listFiles() ?: return emptyList()
    return allFile.filter { !it.isFile }
}

fun File.subFileList(): List<File> {
    if (this.isFile) {
        throw RuntimeException("not a folder")
    }
    val allFile = this.listFiles() ?: return emptyList()
    return allFile.filter { it.isFile }
}

fun File.subImageList(): List<File> {
    return this.subFileList().filter { Arrays.stream(imageExtensionNameArray).anyMatch { t -> it.extension == t } }
}
