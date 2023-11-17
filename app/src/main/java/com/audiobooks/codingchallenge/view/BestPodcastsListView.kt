package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel

@Composable
fun BestPodcastsListView(
    podcasts: List<Podcast>,
    viewModel: GetBestPodcastsViewModel,
    lazyListState: LazyListState
) {
    LazyColumn(state = lazyListState) {
        itemsIndexed(podcasts) { index, _ ->
            BestPodcastItemView(podcast = podcasts[index], viewModel)
            Divider(color = Color.LightGray, thickness = 0.5.dp)

            if (index == podcasts.size - 1) {
                val lastVisibleItemIndex =
                    lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                val totalItems =
                    remember { derivedStateOf { lazyListState.layoutInfo } }.value.totalItemsCount

                if (lastVisibleItemIndex == index && index == totalItems - 1) {
                    viewModel.loadNextBatch()
                }
            }
        }
    }
}