package com.audiobooks.codingchallenge

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GetBestPodcastsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioBooks(viewModel)
                }
            }
        }
    }
}

@Composable
fun AudioBooks(viewModel: GetBestPodcastsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bestPodcasts") {
        composable("bestPodcasts") {
            BestPodcastsView(viewModel)
        }
        composable("podcastDetails/{podcastId}") {
            PodcastView(viewModel)
        }
    }

    val navigationEvent = viewModel.navigationEvent.value
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
        viewModel.clearNavigationEvent()
    }

}