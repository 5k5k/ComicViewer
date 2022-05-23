package com.morladim.comicviewer.database

import androidx.lifecycle.LiveData
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewHistoryRepository @Inject constructor() {

    @Inject
    lateinit var viewHistory: ViewHistoryDao

    @DatabaseRepository.DbExecutor
    @Inject
    lateinit var executor: Executor

    fun insertOrUpdate(item: ViewHistoryEntity) {
        executor.execute { viewHistory.insert(item) }
    }

    fun findByPath(path: String): LiveData<ViewHistoryEntity?> {
        return viewHistory.findByPath(path)
    }
}