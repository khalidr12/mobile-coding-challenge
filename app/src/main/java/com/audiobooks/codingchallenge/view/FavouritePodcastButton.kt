package com.audiobooks.codingchallenge.view

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.ui.theme.FavouriteRed
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel

@Composable
fun FavouritePodcastButton(viewModel: PodcastViewModel, podcast: PodcastEntity){
    val isFavourite = viewModel.toggleFavorite.value
    val backgroundColor = FavouriteRed

    val customColors = ButtonDefaults.buttonColors(
        containerColor = backgroundColor
    )

    Button(
        onClick = { viewModel.toggleFavorite(podcastId = podcast.id) },
        colors = customColors
    ){
        Text(
            text = if(isFavourite) "Unfavourite" else " Favourite",
        )
    }
}