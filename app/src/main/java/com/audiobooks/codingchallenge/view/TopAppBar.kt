package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel

@Composable
fun TopAppBar(viewModel: PodcastViewModel){
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                viewModel.navigateBack()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "Back",
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}