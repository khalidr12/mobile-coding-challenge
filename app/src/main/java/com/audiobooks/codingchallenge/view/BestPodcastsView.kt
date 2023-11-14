package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsView(
    viewModel: GetBestPodcastsViewModel,
) {
    when (val viewState = viewModel.viewState.value) {
        is BestPodcastsViewState.Loading -> {
            ShowText(sampleText = "Loading")
        }

        is BestPodcastsViewState.Error -> {
            ShowText(sampleText = viewState.errorMessage)
        }

        is BestPodcastsViewState.Success -> {
            BestPodcastsListView(viewState.podcasts, viewModel)
        }
    }
}

@Composable
fun BestPodcastsListView(podcasts: List<Podcast>, viewModel: GetBestPodcastsViewModel){
    Column (
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "Podcasts",
            fontWeight = Bold,
            fontSize = 22.sp
        )
        LazyColumn {
            items(podcasts.size){
                BestPodcastItemView(podcast = podcasts[it], viewModel)
            }
        }
    }
}


@Composable
fun ShowText(sampleText : String){
    Text(
        text = "Hello $sampleText!",
        modifier = Modifier
    )
}