package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.PodcastEntity

data class BestPodcastsResponse(
    val podcasts: List<PodcastEntity>,
    val has_next: Boolean,
    val next_page_number: Int?
)