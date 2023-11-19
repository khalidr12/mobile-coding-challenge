package com.audiobooks.codingchallenge.model

import com.audiobooks.codingchallenge.database.PodcastEntity
import java.lang.Exception

sealed class BestPodcastsViewState {
    object Loading : BestPodcastsViewState()
    data class Success(val podcasts: List<PodcastEntity>) : BestPodcastsViewState()
    data class Error(val exception: Exception) : BestPodcastsViewState()
}