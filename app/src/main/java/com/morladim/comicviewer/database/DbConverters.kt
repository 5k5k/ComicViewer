package com.morladim.comicviewer.database

import androidx.room.TypeConverter
import java.util.*

/**
 *
 * @Author 5k5k
 * @Date 2021/12/6
 */
class DbConverters {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun timestampToDate(timestamp: Long) = Date(timestamp)
}