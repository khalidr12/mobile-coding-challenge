package com.audiobooks.codingchallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingView(){
    LoadingCellView()
}

@Composable
fun LoadingCellView(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .size(65.dp, 65.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(180.dp)
                    .background(Color.LightGray)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(120.dp)
                    .background(Color.LightGray)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
}