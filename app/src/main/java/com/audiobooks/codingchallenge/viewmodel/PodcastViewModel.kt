package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.response.Podcast
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.model.PodcastViewState
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import com.audiobooks.codingchallenge.usecase.Result
import com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase.GetBestPodcastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PodcastViewModel @Inject constructor(
    private var useCase: GetBestPodcastsUseCase,
) : ViewModel() {

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    private val _podcast = mutableStateOf<Podcast?>(null)
    val podcast: State<Podcast?> get() = _podcast

    private val _toggleFavorite = mutableStateOf(false)
    val toggleFavorite: State<Boolean> get() = _toggleFavorite


    fun loadPodcast(podcastId: String) {
        viewModelScope.launch {
            useCase.getPodcast(podcastId).collect{
                if (it != null){
                    _podcast.value = it
                    _toggleFavorite.value = podcast.value?.isFavourite ?: false
                }
            }
        }
    }
    fun navigateBack(){
        _navigationEvent.value = podcast.value?.let { NavigationEvent.NavigateBack(it) }
    }

    fun toggleFavorite(podcastId: String){
        viewModelScope.launch {
            useCase.updatePodcast(podcastId)
            _toggleFavorite.value = !podcast.value?.isFavourite!!
        }
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}