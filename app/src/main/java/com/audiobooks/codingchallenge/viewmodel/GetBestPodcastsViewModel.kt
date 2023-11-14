package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.database.Podcast
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GetBestPodcastsViewModel @Inject constructor(
    private var getBestPodcastsRepository: GetBestPodcastsRepository,
): ViewModel() {

    private val _viewState = mutableStateOf<BestPodcastsViewState>(BestPodcastsViewState.Loading)
    val viewState: State<BestPodcastsViewState> get() = _viewState

    private val _isFavourite = mutableStateOf(false)
    val isFavourite: State<Boolean> get() = _isFavourite

    private val _podcast = mutableStateOf<Podcast?>(null)
    val podcast: State<Podcast?> get() = _podcast

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    init {
        getBestPodcasts()
    }

    private fun getBestPodcasts(){
        viewModelScope.launch {
            getBestPodcastsRepository.refreshPodcasts()
            val podcasts = getBestPodcastsRepository.getAllPodcasts()
            _viewState.value = BestPodcastsViewState.Success(
                podcasts = podcasts
            )
        }
    }

    fun podcastSelected(podcastId: String){
        getPodcastById(podcastId = podcastId)
        _navigationEvent.value = NavigationEvent.NavigateToPodcastView(podcastId = podcastId)
    }
    fun navigateBack(){
        _navigationEvent.value = NavigationEvent.NavigateBack
    }
    fun toggleFavorite(podcastId: String) {
        if(_viewState.value is BestPodcastsViewState.Success){
            val updatedPodcasts = (_viewState.value as BestPodcastsViewState.Success).podcasts.map {
                if (it.id == podcastId) {
                    _isFavourite.value = !it.isFavourite
                    it.copy(isFavourite = !it.isFavourite)
                } else {
                    it
                }
            }
            _viewState.value = BestPodcastsViewState.Success(updatedPodcasts)
            // Update the Room database
            viewModelScope.launch {
                getBestPodcastsRepository.updatePodcast(
                    podcastId,
                    (_viewState.value as BestPodcastsViewState.Success)
                        .podcasts.find { it.id == podcastId }?.isFavourite ?: false
                )
            }
        }

    }
    private fun getPodcastById(podcastId: String) {
        viewModelScope.launch {
            _podcast.value = getBestPodcastsRepository.getPodcastById(podcastId)
        }
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}