package com.morladim.comicviewer.folder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.morladim.comicviewer.common.subfolderList
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

/**
 * @author 5k5k
 * Created 2022/5/20
 */
@HiltViewModel
class FolderViewModel @Inject constructor(private val folderRepository: FolderRepository) : ViewModel() {

    var currentPath: String = folderRepository.rootPath

    val currentData: MutableLiveData<List<File>> = MutableLiveData()

    fun init() {
        if (currentPath == folderRepository.rootPath) {
            currentData.postValue(folderRepository.getRoot())
        }
    }

    fun onItemClick(file: File) {
        currentPath = file.absolutePath
        currentData.postValue(file.subfolderList())
    }

    fun currentIsRoot(): Boolean {
        return currentPath == folderRepository.rootPath
    }

    fun toUpFolder() {
        val position = currentPath.lastIndexOf(File.separator)
        currentPath = currentPath.substring(0, position)
        currentData.postValue((File(currentPath).subfolderList()))
    }
}