package com.audiobooks.codingchallenge.api.repository

import androidx.paging.PagingData
import com.audiobooks.codingchallenge.api.response.Podcast
import com.audiobooks.codingchallenge.database.PodcastEntity
import kotlinx.coroutines.flow.Flow

interface IGetBestPodcastsRepository {

    fun getPodcasts(): Flow<PagingData<Podcast>>
    suspend fun getPodcastById(podcastId: String): Flow<Podcast?>
    suspend fun updatePodcast(podcastId: String)

}