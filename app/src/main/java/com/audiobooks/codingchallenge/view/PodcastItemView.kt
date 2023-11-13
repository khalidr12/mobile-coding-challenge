package com.audiobooks.codingchallenge.view

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.audiobooks.codingchallenge.api.response.Podcast

@Composable
fun PodcastItem(podcast: Podcast){
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        val painter: Painter = rememberImagePainter(podcast.image)
        Image(
            painter = painter,
            contentDescription = null, // Add content description if needed
            modifier = Modifier
                .size(100.dp, 100.dp) // Adjust size as needed
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                text = podcast.title,
            )

            Text(
                text = podcast.publisher,
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp
            )
        }
    }
}