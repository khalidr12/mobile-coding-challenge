package com.audiobooks.codingchallenge.model

import com.audiobooks.codingchallenge.database.Podcast

sealed class BestPodcastsViewState {
    object Loading : BestPodcastsViewState()
    data class Success(val podcasts: List<Podcast>) : BestPodcastsViewState()
    data class Error(val errorMessage: String) : BestPodcastsViewState()
}