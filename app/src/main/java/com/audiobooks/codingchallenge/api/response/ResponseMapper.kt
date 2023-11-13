package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.model.BestPodcastModel

fun BestPodcastsResponse.toBestPodcastsModel() : BestPodcastModel {
    return BestPodcastModel(
        id = this.id,
        name = this.name,
        total = this.total,
        hasNext = this.hasNext,
        podcasts = this.podcasts,
        parentId = this.parentId,
        pageNumber = this.pageNumber,
        hasPrevious = this.hasPrevious,
        listenNotesUrl = this.listenNotesUrl,
        nextPageNumber = this.nextPageNumber,
        previousPageNumber = this.previousPageNumber
    )
}