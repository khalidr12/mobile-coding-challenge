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

    suspend fun initializeData() {
        val podcastsFromDB = podcastsDAO.getAllPodcasts()
        if (podcastsFromDB.isEmpty()) {
            val podcastsFromApi = audioBookService.getBestPodcasts().body()?.podcasts ?: emptyList()
            podcastsDAO.insertPodcasts(podcastsFromApi)
        } else {
            val latestPodcastsFromApi = audioBookService.getBestPodcasts().body()?.podcasts ?: emptyList()

            for (apiPodcast in latestPodcastsFromApi) {
                val existingPodcast = podcastsFromDB.find { it.id == apiPodcast.id }

                if (existingPodcast != null) {
                    if (!existingPodcast.isFavourite) {
                        podcastsDAO.update(apiPodcast)
                    }
                } else {
                    podcastsDAO.insert(apiPodcast)
                }
            }
        }
    }
    suspend fun getAllPodcasts() : List<Podcast> {
        return podcastsDAO.getAllPodcasts()
    }

    suspend fun getPodcastById(podcastId: String) : Podcast? {
        return podcastsDAO.getPodcastById(podcastId = podcastId)
    }

    private suspend fun insertPodcasts(podcasts: List<Podcast>) {
        return podcastsDAO.insertPodcasts(podcasts = podcasts)
    }

    suspend fun updatePodcast(podcastId: String): Podcast? {
        val podcast = getPodcastById(podcastId)
        if (podcast != null) {
            podcastsDAO.update(podcast.copy(isFavourite = !podcast.isFavourite))
        }
        return podcast
    }

    override suspend fun getBestPodcasts(): Response<BestPodcastsResponse> {
        return audioBookService.getBestPodcasts()
    }
}