package com.audiobooks.codingchallenge.database.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.audiobooks.codingchallenge.api.mediator.PodcastMediator
import com.audiobooks.codingchallenge.api.service.AudioBookService
import com.audiobooks.codingchallenge.database.AppDatabase
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.database.PodcastPagingDAO
import com.audiobooks.codingchallenge.database.PodcastsEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "audio_books"
        ).build()
    }

    @Provides
    fun providePodcastDao(appDatabase: AppDatabase): BestPodcastsDAO {
        return appDatabase.podcastDao()
    }

    @Provides
    fun providePodcastsDao(appDatabase: AppDatabase): PodcastPagingDAO {
        return appDatabase.podcastsDao()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePager(
        database: AppDatabase,
        api: AudioBookService
    ) : Pager<Int, PodcastEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PodcastMediator(database, api),
            pagingSourceFactory = {database.podcastDao().pagingSource()},
        )
    }
}
