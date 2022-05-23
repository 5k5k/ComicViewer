package com.morladim.comicviewer.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseRepository {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DbExecutor

    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext context: Context,
        @DbExecutor executor: Executor
    ): ComicDatabase {
        return Room.databaseBuilder(
            context,
            ComicDatabase::class.java, "comic.db"
        )
//            .addMigrations(MIGRATION_1_2)
            .setQueryExecutor(executor).build()
    }

    @Provides
    @Singleton
    @DbExecutor
    fun dbExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun getViewHistoryDao(db: ComicDatabase): ViewHistoryDao {
        return db.viewHistoryDao()
    }

    @Provides
    @Singleton
    fun otherExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }
}