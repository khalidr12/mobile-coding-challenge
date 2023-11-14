package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PodcastViewModel @Inject constructor(
    private var getBestPodcastsRepository: GetBestPodcastsRepository,
) : ViewModel() {

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    private val _podcast = mutableStateOf<Podcast?>(null)
    val podcast: State<Podcast?> get() = _podcast

    private val _toggleFavorite = mutableStateOf(false)
    val toggleFavorite: State<Boolean> get() = _toggleFavorite


    fun loadPodcast(podcastId: String) {
        viewModelScope.launch {
            _podcast.value = getBestPodcastsRepository.getPodcastById(podcastId)
            if(_podcast.value != null){
                _toggleFavorite.value = podcast.value?.isFavourite ?: false
            }
        }
    }
    fun navigateBack(){
        _navigationEvent.value = NavigationEvent.NavigateBack
    }

    fun toggleFavorite(podcastId: String){
        viewModelScope.launch {
            getBestPodcastsRepository.updatePodcast(
                podcastId = podcastId
            )
            loadPodcast(podcastId = podcastId)
        }
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}