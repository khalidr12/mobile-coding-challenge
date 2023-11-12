package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.api.response.BestPodcastsResponse
import retrofit2.Response

interface IGetBestPodcastsRepository {
    suspend fun getBestPodcasts() : Response<BestPodcastsResponse>
}