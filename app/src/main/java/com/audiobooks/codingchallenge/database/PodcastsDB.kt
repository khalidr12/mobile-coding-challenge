package com.audiobooks.codingchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PodcastEntity::class, PodcastsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun podcastDao(): BestPodcastsDAO
    abstract fun podcastsDao(): PodcastPagingDAO
}