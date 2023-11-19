package com.audiobooks.codingchallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PodcastPagingDAO {
    @Query("SELECT * FROM podcastPaging where id = :id")
    suspend fun getEntity(id: String): PodcastsEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPodcast(entity: PodcastsEntity)

    @Query("DELETE FROM podcastPaging")
    suspend fun clearAll()
}