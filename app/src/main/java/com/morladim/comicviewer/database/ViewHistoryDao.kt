package com.morladim.comicviewer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ViewHistoryDao {

    @Query("SELECT * FROM view_history WHERE path = :path LIMIT 1")
    fun findByPath(path: String): LiveData<ViewHistoryEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(viewHistoryEntity: ViewHistoryEntity)
}