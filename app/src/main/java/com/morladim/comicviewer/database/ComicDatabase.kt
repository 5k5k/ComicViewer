package com.morladim.comicviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ViewHistoryEntity::class],
    version = 1
)
@TypeConverters(DbConverters::class)
abstract class ComicDatabase : RoomDatabase() {

    abstract fun viewHistoryDao(): ViewHistoryDao

}