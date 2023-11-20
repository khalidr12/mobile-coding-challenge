package com.audiobooks.codingchallenge.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BestPodcastsDAO {

    @Query("SELECT * FROM podcasts WHERE id = :podcastId")
    suspend fun getPodcastById(podcastId: String): PodcastEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPodcasts(podcasts: List<PodcastEntity>)

    @Update
    suspend fun update(podcast: PodcastEntity)

    @Query("SELECT * FROM podcasts")
    fun pagingSource(): PagingSource<Int, PodcastEntity>

    @Query("DELETE FROM podcasts")
    suspend fun clearAll()
}
