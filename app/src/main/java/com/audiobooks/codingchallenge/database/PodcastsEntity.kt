package com.audiobooks.codingchallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcastPaging")
data class PodcastsEntity (
    @PrimaryKey val id: String,
    val hasNextPage: Boolean,
    val nextPageNumber: Int?
)