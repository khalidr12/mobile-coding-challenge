package com.audiobooks.codingchallenge.model

import com.audiobooks.codingchallenge.api.response.Podcast

data class BestPodcastModel(
    val id: Int?,
    val name: String?,
    val total: Int?,
    val hasNext: Boolean?,
    val podcasts: List<Podcast>,
    val parentId: Int?,
    val pageNumber: Int?,
    val hasPrevious: Boolean?,
    val listenNotesUrl: String?,
    val nextPageNumber: Int?,
    val previousPageNumber: Int?
)
