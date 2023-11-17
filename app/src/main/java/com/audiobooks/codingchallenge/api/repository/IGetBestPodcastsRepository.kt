package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.database.Podcast
interface IGetBestPodcastsRepository {
    suspend fun getBestPodcastsFromApi() : List<Podcast>
    suspend fun loadBatchFromRoom() : List<Podcast>
    suspend fun getAllPodcastsUntilOffset() : List<Podcast>
    suspend fun initializeData()
    suspend fun isNotLastPage() : Boolean
}