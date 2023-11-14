package com.audiobooks.codingchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import com.audiobooks.codingchallenge.ui.theme.MyApplicationTheme
import com.audiobooks.codingchallenge.view.BestPodcastsView
import com.audiobooks.codingchallenge.view.PodcastView
import com.audiobooks.codingchallenge.viewmodel.GetBestPodcastsViewModel
import com.audiobooks.codingchallenge.viewmodel.PodcastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val getBestPodcastViewModel: GetBestPodcastsViewModel by viewModels()
    private val podcastViewModel: PodcastViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioBooks()
                }
            }
        }
    }

    @Composable
    fun AudioBooks() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "bestPodcasts") {
            composable("bestPodcasts") {
                BestPodcastsView(getBestPodcastViewModel)
            }
            composable("podcastDetails/{podcastId}") {
                it.arguments?.getString("podcastId")
                    ?.let { it1 -> podcastViewModel.loadPodcast(it1) }
                PodcastView(podcastViewModel)
            }
        }

        val navigationEvent = getBestPodcastViewModel.navigationEvent.value
        if (navigationEvent != null) {
            when (navigationEvent) {
                is NavigationEvent.NavigateToPodcastView -> {
                    navController.navigate("podcastDetails/${navigationEvent.podcastId}")
                }
                is NavigationEvent.NavigateToPodcastList -> {
                    navController.navigate("bestPodcasts")
                }
                is NavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }
            }
            getBestPodcastViewModel.clearNavigationEvent()
        }

        when (podcastViewModel.navigationEvent.value) {
            is NavigationEvent.NavigateBack -> {
                getBestPodcastViewModel.loadPodcasts()
                navController.popBackStack()
            }

            else -> {
                Log.ERROR
            }
        }
        podcastViewModel.clearNavigationEvent()
    }
}