package com.audiobooks.codingchallenge.api.service

import com.audiobooks.codingchallenge.api.response.BestPodcastsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioBookService {
    @GET("best_podcasts")
    suspend fun getBestPodcasts() : Response<BestPodcastsResponse>
}