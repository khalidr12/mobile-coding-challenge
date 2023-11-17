package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.utils.formatHtmlText
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel

@Composable
fun PodcastDetails(
    podcast: Podcast,
    viewModel: PodcastViewModel
){
    Column(
        modifier = Modifier
            .padding(15.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = podcast.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = podcast.publisher,
            fontSize = 13.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(15.dp))
        val painter: Painter = rememberImagePainter(podcast.image)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 200.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(15.dp))
        FavouritePodcastButton(viewModel, podcast)

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = formatHtmlText(podcast.description),
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp,
        )
    }
}