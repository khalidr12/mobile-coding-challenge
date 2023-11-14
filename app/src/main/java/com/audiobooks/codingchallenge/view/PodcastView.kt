package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.audiobooks.codingchallenge.R
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel

@Composable
fun PodcastView(
    viewModel: PodcastViewModel
){
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp, 15.dp)
                    .clickable {
                        viewModel.navigateBack()
                    },
            )
            Text(text = "Back", modifier = Modifier.padding(start = 8.dp))
        }
        viewModel.podcast.value?.let { podcast ->
            Text(text = podcast.title)
            Text(text = podcast.publisher)
            val painter: Painter = rememberImagePainter(podcast.image)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(text = podcast.description)
            Button(
                onClick = { viewModel.toggleFavorite(podcastId = podcast.id) }
            ){
                Text(text = viewModel.toggleFavorite.value.toString())
            }
        }
    }
}