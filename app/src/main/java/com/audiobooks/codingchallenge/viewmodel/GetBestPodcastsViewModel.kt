package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
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

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    init {
        loadPodcasts()
    }

    fun loadPodcasts(){
        viewModelScope.launch {
            getBestPodcastsRepository.initializeData()
            val podcasts = getBestPodcastsRepository.getAllPodcasts()
            _viewState.value = BestPodcastsViewState.Success(
                podcasts = podcasts
            )
        }
    }

    fun podcastSelected(podcastId: String){
        _navigationEvent.value = NavigationEvent.NavigateToPodcastView(podcastId = podcastId)
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}