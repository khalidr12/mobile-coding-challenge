package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.audiobooks.codingchallenge.R
import com.audiobooks.codingchallenge.api.response.Podcast

@Composable
fun PodcastView(
    podcast: Podcast,
    navController: NavController
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
                        navController.popBackStack()
                   },
            )
            Text(text = "Back", modifier = Modifier.padding(start = 8.dp))
        }
        Text(text = podcast.title)
        Text(text = podcast.publisher)
        val painter: Painter = rememberImagePainter(podcast.image)
        Image(
            painter = painter,
            contentDescription = null, // Add content description if needed
            modifier = Modifier
                .size(100.dp, 100.dp) // Adjust size as needed
                .clip(RoundedCornerShape(10.dp))
        )
        Text(text = podcast.description)
    }
}