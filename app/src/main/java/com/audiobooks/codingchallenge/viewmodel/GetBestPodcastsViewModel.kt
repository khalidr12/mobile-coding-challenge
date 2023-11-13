package com.audiobooks.codingchallenge.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.codingchallenge.model.BestPodcastsViewState
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
    init {
        getBestPodcasts()
    }

    fun getBestPodcasts(){
        viewModelScope.launch {
            getBestPodcastsUseCase().collect { result ->
                when(result){
                    is Result.Loading -> {
                        _viewState.value = BestPodcastsViewState.Loading
                    }
                    is Result.Success -> {
                       _viewState.value = BestPodcastsViewState.Success(
                           podcasts = result.data.podcasts
                       )
                    }
                    is Result.Error -> {
                        _viewState.value = BestPodcastsViewState.Error(result.exception.message.toString())
                    }
                }

            }
        }
    }
}