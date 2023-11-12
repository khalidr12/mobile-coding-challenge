package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.api.response.BestPodcastsResponse
import com.audiobooks.codingchallenge.api.service.AudioBookService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBestPodcastsRepository @Inject constructor(
    private val audioBookService: AudioBookService
) : IGetBestPodcastsRepository {
    override suspend fun getBestPodcasts(): Response<BestPodcastsResponse> {
        return audioBookService.getBestPodcasts()
    }
}