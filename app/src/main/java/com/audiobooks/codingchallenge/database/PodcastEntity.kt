package com.audiobooks.codingchallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcasts")
data class Podcast(
    @PrimaryKey val id: String,
    val image: String,
    val title: String,
    val publisher: String,
    val thumbnail: String,
    val description: String,
    val isFavourite: Boolean = false
)