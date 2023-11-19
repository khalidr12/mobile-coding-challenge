package com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase

import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.usecase.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception


class GetBestPodcastsUseCase @Inject constructor(
    private val repository: IGetBestPodcastsRepository,
) {

    fun getPodcasts() = repository.getPodcasts()
}
