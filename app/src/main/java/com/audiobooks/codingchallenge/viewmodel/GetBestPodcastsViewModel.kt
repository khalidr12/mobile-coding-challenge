package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import com.audiobooks.codingchallenge.usecase.Result
import com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase.GetBestPodcastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GetBestPodcastsViewModel @Inject constructor(
    private var getBestPodcastsUseCase: GetBestPodcastsUseCase
): ViewModel() {

    private val _viewState = mutableStateOf<BestPodcastsViewState>(BestPodcastsViewState.Loading)
    val viewState: State<BestPodcastsViewState> get() = _viewState

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    init {
        initPodcasts()
    }

    private fun initPodcasts(){
        viewModelScope.launch {
            getBestPodcastsUseCase.initialize().collect {
                when(it){
                    is Result.Loading -> {
                        _viewState.value = BestPodcastsViewState.Loading
                    }

                    is Result.Error -> {
                        _viewState.value = BestPodcastsViewState.Error(it.exception)
                    }

                    is Result.Success -> {
                        _viewState.value = BestPodcastsViewState.Success(it.data)
                    }
                }
            }
        }
    }
    fun loadPodcasts(){
        viewModelScope.launch {
            getBestPodcastsUseCase.loadData().collect {
                when(it){
                    is Result.Loading -> {
                        _viewState.value = BestPodcastsViewState.Loading
                    }

                    is Result.Error -> {
                        _viewState.value = BestPodcastsViewState.Error(it.exception)
                    }

                    is Result.Success -> {
                        _viewState.value = BestPodcastsViewState.Success(it.data)
                    }
                }
            }
        }
    }

    fun loadNextBatch() {
        viewModelScope.launch {
           getBestPodcastsUseCase.loadBatch().collect {
               when(it){
                   is Result.Error -> {
                       _viewState.value = BestPodcastsViewState.Error(it.exception)
                   }

                   is Result.Success -> {
                       _viewState.value = BestPodcastsViewState.Success(it.data)
                   }

                   is Result.Loading -> {
                       _viewState.value = BestPodcastsViewState.Loading
                   }
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