package com.audiobooks.codingchallenge.model

import com.audiobooks.codingchallenge.database.Podcast
import java.lang.Exception

sealed class BestPodcastsViewState {
    object Loading : BestPodcastsViewState()
    data class Success(val podcasts: List<Podcast>) : BestPodcastsViewState()
    data class Error(val exception: Exception) : BestPodcastsViewState()
}