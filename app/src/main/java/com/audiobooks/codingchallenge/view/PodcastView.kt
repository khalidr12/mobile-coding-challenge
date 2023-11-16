package com.audiobooks.codingchallenge.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.audiobooks.codingchallenge.R
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.ui.theme.FavouriteRed
import com.audiobooks.codingchallenge.utils.formatHtmlText
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PodcastView(
    viewModel: PodcastViewModel
){
    Scaffold (
        topBar = { TopAppBar(viewModel = viewModel) },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewModel.podcast.value?.let { podcast ->
                    Text(
                        text = podcast.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = podcast.publisher)
                    val painter: Painter = rememberImagePainter(podcast.image)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    FavouritePodcastButton(viewModel, podcast)
                    Text(text = formatHtmlText(podcast.description))
                }
            }
        }
    )
}

@Composable
fun FavouritePodcastButton(viewModel: PodcastViewModel, podcast: Podcast){
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

@Composable
fun TopAppBar(viewModel: PodcastViewModel){
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
}