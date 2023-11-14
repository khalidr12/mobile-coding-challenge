package com.audiobooks.codingchallenge.navigation


sealed class NavigationEvent {
    data class NavigateToPodcastView(
        val podcastId: String
    ) : NavigationEvent()
    object NavigateToPodcastList : NavigationEvent()

    object NavigateBack : NavigationEvent()
}