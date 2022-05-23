package com.morladim.comicviewer.viewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.morladim.comicviewer.database.ViewHistoryEntity
import com.morladim.comicviewer.database.ViewHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewerViewModel @Inject constructor(private val viewHistoryRepository: ViewHistoryRepository) : ViewModel() {

    var hasInitScroll = false
    private lateinit var path: String
    lateinit var viewHistory: LiveData<ViewHistoryEntity?>

    fun init(path: String) {
        hasInitScroll = false
        this.path = path
        viewHistory = getHistory()
    }

    private fun getHistory(): LiveData<ViewHistoryEntity?> {
        return viewHistoryRepository.findByPath(path)
    }

    fun updateProcess(offset: Int, percent: Float) {
        val now = Date()
        if (viewHistory.value == null) {
            val finish = percent > 99.9f
            val entity = ViewHistoryEntity(createAt = now, updateAt = now, path = path, scroll = offset, finish = finish, percent = percent, id = null)
            viewHistoryRepository.insertOrUpdate(entity)
        } else {
            val v: ViewHistoryEntity = viewHistory.value!!
            if (offset > v.scroll && percent > v.percent) {
                val finish = percent > 99.9f
                val entity = v.copy(scroll = offset, updateAt = now, percent = percent, finish = finish)
                viewHistoryRepository.insertOrUpdate(entity)
            }
        }
    }
}