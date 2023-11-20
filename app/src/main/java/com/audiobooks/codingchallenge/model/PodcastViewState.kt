package com.audiobooks.codingchallenge.model

import com.audiobooks.codingchallenge.api.response.Podcast
import com.audiobooks.codingchallenge.database.PodcastEntity
import java.lang.Exception

sealed class PodcastViewState {
    object Loading : PodcastViewState()
    data class Success(val podcast: Podcast) : PodcastViewState()
    data class Error(val exception: Exception) : PodcastViewState()
}