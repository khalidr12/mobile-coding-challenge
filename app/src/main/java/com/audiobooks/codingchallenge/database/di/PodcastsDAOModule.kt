package com.audiobooks.codingchallenge.database.di

import android.content.Context
import androidx.room.Room
import com.audiobooks.codingchallenge.database.AppDatabase
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import dagger.Binds
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
}
