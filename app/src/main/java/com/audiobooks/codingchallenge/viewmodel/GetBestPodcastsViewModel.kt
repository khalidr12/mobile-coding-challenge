package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.audiobooks.codingchallenge.database.PodcastsEntity
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
import com.audiobooks.codingchallenge.navigation.NavigationEvent
import com.audiobooks.codingchallenge.usecase.Result
import com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase.GetBestPodcastsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GetBestPodcastsViewModel @Inject constructor(
    getBestPodcastsUseCase: GetBestPodcastsUseCase
): ViewModel() {

    private val _navigationEvent = mutableStateOf<NavigationEvent?>(null)
    val navigationEvent: State<NavigationEvent?> get() = _navigationEvent

    var podcastsFlow = getBestPodcastsUseCase.getPodcasts().cachedIn(viewModelScope)

    fun podcastSelected(podcastId: String){
        _navigationEvent.value = NavigationEvent.NavigateToPodcastView(podcastId = podcastId)
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}