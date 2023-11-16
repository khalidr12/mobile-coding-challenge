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

    val isLastBatch: Boolean = false
    init {
        initPodcasts()
    }

    private fun initPodcasts(){
        viewModelScope.launch{
            getBestPodcastsRepository.initializeData() // Gets all data from api and stores to db
            loadNextBatch()
        }
    }
    fun loadPodcasts(){
        viewModelScope.launch {
            val podcasts = getBestPodcastsRepository.getAllPodcasts()
            _viewState.value = BestPodcastsViewState.Success(
                podcasts = podcasts
            )
        }
    }

    fun loadNextBatch() {
        if(!isLastBatch){
            viewModelScope.launch {
                getBestPodcastsRepository.loadBatch() // gets the next batch of podcasts
                _viewState.value = BestPodcastsViewState.Success(
                    podcasts = getBestPodcastsRepository.getAllPodcasts() // load all available podcasts
                )
                if(viewState.value is BestPodcastsViewState.Success){
                    println("PODCASTS: " + (viewState.value as BestPodcastsViewState.Success).podcasts.size)
                }
            }
        }
    }
    fun podcastSelected(podcastId: String){
        _navigationEvent.value = NavigationEvent.NavigateToPodcastView(podcastId = podcastId)
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}