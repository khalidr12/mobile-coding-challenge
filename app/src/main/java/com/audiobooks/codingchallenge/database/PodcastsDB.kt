package com.audiobooks.codingchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Podcast::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun podcastDao(): BestPodcastsDAO
}