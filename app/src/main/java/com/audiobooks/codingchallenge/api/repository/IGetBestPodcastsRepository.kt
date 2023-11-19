package com.audiobooks.codingchallenge.api.repository

import androidx.paging.PagingData
import com.audiobooks.codingchallenge.database.PodcastEntity
import kotlinx.coroutines.flow.Flow

interface IGetBestPodcastsRepository {

    fun getPodcasts(): Flow<PagingData<PodcastEntity>>
    suspend fun getPodcastById(podcastId: String): PodcastEntity?
    suspend fun updatePodcast(podcastId: String): PodcastEntity?

}