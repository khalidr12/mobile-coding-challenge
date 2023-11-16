package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.Podcast

data class BestPodcastsResponse(
    val podcasts: List<Podcast>,
    val has_next: Boolean
)

