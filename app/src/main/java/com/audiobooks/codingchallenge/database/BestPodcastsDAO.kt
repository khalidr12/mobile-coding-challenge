package com.audiobooks.codingchallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BestPodcastsDAO {
    @Query("SELECT * FROM podcasts LIMIT :limit")
    suspend fun getAllPodcasts(limit: Int): List<Podcast>

    @Query("SELECT * FROM podcasts WHERE id = :podcastId")
    suspend fun getPodcastById(podcastId: String): Podcast?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPodcasts(podcasts: List<Podcast>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(podcast: Podcast)

    @Query("SELECT * FROM podcasts LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedPodcasts(limit: Int, offset: Int): List<Podcast>
    @Update
    suspend fun update(podcast: Podcast)
}
