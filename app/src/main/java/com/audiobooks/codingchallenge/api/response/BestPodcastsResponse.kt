package com.audiobooks.codingchallenge.api.response

data class BestPodcastsResponse(
    val id: String,
    val podcasts: List<Podcast>,
    val has_next: Boolean,
    val next_page_number: Int? = null
)

data class Podcast(
    val id: String,
    val image: String,
    val title: String,
    val publisher: String,
    val thumbnail: String,
    val description: String,
    val isFavourite: Boolean = false
)