package com.morladim.comicviewer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "view_history")
data class ViewHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "create_at") val createAt: Date,
    @ColumnInfo(name = "update_at") val updateAt: Date,
    @ColumnInfo(name = "path") val path: String,
    @ColumnInfo(name = "scroll") val scroll: Int,
    @ColumnInfo(name = "finish") val finish: Boolean,
    @ColumnInfo(name = "percent") val percent: Float,
)
