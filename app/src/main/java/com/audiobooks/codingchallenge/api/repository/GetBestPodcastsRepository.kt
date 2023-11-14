package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.api.response.BestPodcastsResponse
import com.audiobooks.codingchallenge.api.service.AudioBookService
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.Podcast
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBestPodcastsRepository @Inject constructor(
    private val audioBookService: AudioBookService,
    private val podcastsDAO: BestPodcastsDAO
) : IGetBestPodcastsRepository {

    suspend fun getAllPodcasts() : List<Podcast> {
        return podcastsDAO.getAllPodcasts()
    }

    suspend fun getPodcastById(podcastId: String) : Podcast? {
        return podcastsDAO.getPodcastById(podcastId = podcastId)
    }

    private suspend fun insertPodcasts(podcasts: List<Podcast>) {
        return podcastsDAO.insertPodcasts(podcasts = podcasts)
    }

    suspend fun updatePodcast(podcastId: String, isFavourited: Boolean) {
        val podcast = getPodcastById(podcastId)
        podcast?.let {
            insertPodcasts(listOf(it.copy(isFavourite = isFavourited)))
        }
    }

    suspend fun refreshPodcasts() {
        val response = audioBookService.getBestPodcasts()
        if (response.isSuccessful) {
            val podcasts = response.body()?.podcasts ?: emptyList()
            insertPodcasts(podcasts)
        } else {
            // Handle API error
        }
    }

    override suspend fun getBestPodcasts(): Response<BestPodcastsResponse> {
        return audioBookService.getBestPodcasts()
    }
}