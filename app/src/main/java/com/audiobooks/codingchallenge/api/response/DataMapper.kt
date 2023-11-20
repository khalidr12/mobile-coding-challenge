package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.database.PodcastsEntity

fun Podcast.toEntity(): PodcastEntity {
    return PodcastEntity(
        id = id,
        image = image,
        title = title,
        publisher = publisher,
        thumbnail = thumbnail,
        description = description,
        isFavourite = isFavourite
    )
}

fun PodcastEntity.toPodcast(): Podcast {
    return Podcast(
        id = id,
        image = image,
        title = title,
        publisher = publisher,
        thumbnail = thumbnail,
        description = description,
        isFavourite = isFavourite
    )
}

fun BestPodcastsResponse.toEntity(): PodcastsEntity {
    return PodcastsEntity(
        id = "podcasts",
        hasNextPage = has_next,
        nextPageNumber = next_page_number
    )
}