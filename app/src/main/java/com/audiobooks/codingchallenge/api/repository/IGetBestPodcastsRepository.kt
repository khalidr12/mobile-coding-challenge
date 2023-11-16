package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.database.Podcast
interface IGetBestPodcastsRepository {
    suspend fun getBestPodcasts() : List<Podcast>
}