package com.audiobooks.codingchallenge.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.audiobooks.codingchallenge.api.response.Podcast

@Composable
fun PodcastItem(podcast: Podcast){
    Text(
        text = "Hello ${podcast.title}!",
        modifier = Modifier
    )
}