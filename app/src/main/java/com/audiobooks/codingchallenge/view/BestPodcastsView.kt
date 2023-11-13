package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.audiobooks.codingchallenge.api.response.Podcast
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsView(
    viewModel: GetBestPodcastsViewModel
) {
    when (val viewState = viewModel.viewState.value) {
        is BestPodcastsViewState.Loading -> {
            ShowText(sampleText = "Loading")
        }

        is BestPodcastsViewState.Error -> {
            ShowText(sampleText = viewState.errorMessage)
        }

        is BestPodcastsViewState.Success -> {
            BestPodcastsListView(viewState.podcasts)
        }
    }
}

@Composable
fun BestPodcastsListView(podcasts: List<Podcast>){
    LazyColumn {
        items(podcasts.size){
            PodcastItem(podcast = podcasts[it])
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