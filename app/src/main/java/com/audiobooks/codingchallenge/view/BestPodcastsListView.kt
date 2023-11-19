package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsListView(
    podcasts: LazyPagingItems<PodcastEntity>,
    viewModel: GetBestPodcastsViewModel,
    lazyListState: LazyListState
) {
    LazyColumn(state = lazyListState) {
        items(podcasts.itemCount) {
            podcasts[it]?.let { it1 -> BestPodcastItemView(podcast = it1, viewModel) }
            Divider(color = Color.LightGray, thickness = 0.5.dp)
        }
        item {
            if(podcasts.loadState.append is LoadState.Loading){
                LoadingCellView()
            }
        }
    }
}