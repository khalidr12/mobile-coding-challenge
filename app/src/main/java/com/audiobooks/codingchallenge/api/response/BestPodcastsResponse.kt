package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.Podcast

data class BestPodcastsResponse(
    val podcasts: List<Podcast>,
)

//data class Podcast(
//    val id: String,
//    val image: String,
//    val title: String,
//    val publisher: String,
//    val thumbnail: String,
//    val description: String,
//    val isFavourite: Boolean = false
//)

