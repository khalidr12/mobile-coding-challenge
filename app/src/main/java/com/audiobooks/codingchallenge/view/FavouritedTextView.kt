package com.audiobooks.codingchallenge.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.audiobooks.codingchallenge.ui.theme.FavouriteRed

@Composable
fun FavouriteText(isFavourite: Boolean){
    val text = if (isFavourite) "Favourited" else ""
    Text(
        text = text,
        color = FavouriteRed,
        fontSize = 13.sp
    )
}