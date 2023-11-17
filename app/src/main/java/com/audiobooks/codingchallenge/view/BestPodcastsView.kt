package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsView(
    viewModel: GetBestPodcastsViewModel,
) {
    Column (
        modifier = Modifier.padding(15.dp)
    ) {
        val lazyListState = rememberLazyListState()
        Text(
            text = "Podcasts",
            fontWeight = Bold,
            fontSize = 20.sp
        )

        when (val viewState = viewModel.viewState.value) {
            is BestPodcastsViewState.Loading -> {
                LoadingView()
            }

            is BestPodcastsViewState.Error -> {
                var isErrorDialogVisible by remember { mutableStateOf(true) }
                ErrorAlertDialog(
                    exception = viewState.exception,
                    isErrorDialogVisible = isErrorDialogVisible,
                    onDismiss = {
                        isErrorDialogVisible = false
                })
            }

            is BestPodcastsViewState.Success -> {
                BestPodcastsListView(viewState.podcasts, viewModel, lazyListState)
            }
        }
    }
}