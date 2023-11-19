package com.audiobooks.codingchallenge.view

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
            viewModel.podcast.value?.let { podcast ->
                PodcastDetails(viewModel = viewModel, podcast = podcast)
            }
        }
    )
}