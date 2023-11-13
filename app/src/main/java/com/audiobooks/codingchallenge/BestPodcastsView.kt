package com.audiobooks.codingchallenge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsView(
    viewModel: GetBestPodcastsViewModel
) {
    val data = when (val viewState = viewModel.viewState.value) {
        is BestPodcastsViewState.Loading -> {
           "Loading"
        }

        is BestPodcastsViewState.Error -> {
            viewState.errorMessage
        }

        is BestPodcastsViewState.Success -> {
           viewState.podcasts.toString()
        }
    }
    ShowText(sampleText = data)
}

@Composable
fun ShowText(sampleText : String){
    Text(
        text = "Hello $sampleText!",
        modifier = Modifier
    )
}