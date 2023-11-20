package com.audiobooks.codingchallenge.navigation

import com.audiobooks.codingchallenge.api.response.Podcast


sealed class NavigationEvent {
    data class NavigateToPodcastView(
        val podcastId: String
    ) : NavigationEvent()

    data class NavigateBack(
        val podcast: Podcast
    ) : NavigationEvent()
}