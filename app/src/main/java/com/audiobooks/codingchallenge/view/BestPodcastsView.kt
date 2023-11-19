package com.audiobooks.codingchallenge.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsView(
    podcasts: LazyPagingItems<PodcastEntity>,
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

        val context = LocalContext.current
        LaunchedEffect(key1 = podcasts.loadState){
            if(podcasts.loadState.refresh is LoadState.Error){
                Toast.makeText(
                    context,
                    "error: ${(podcasts.loadState.refresh as LoadState.Error).error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Box(modifier = Modifier.fillMaxSize()){
            if(podcasts.loadState.refresh is LoadState.Loading){
                LoadingView()
            } else {
                BestPodcastsListView(podcasts = podcasts, viewModel = viewModel, lazyListState = lazyListState )
            }
        }
    }
}