package com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase

import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetBestPodcastsUseCase @Inject constructor(
    private val repository: IGetBestPodcastsRepository,
) {

    fun getPodcasts() = repository.getPodcasts().flowOn(Dispatchers.IO)

    suspend fun getPodcast(id: String) =  repository.getPodcastById(id).flowOn(Dispatchers.IO)

    suspend fun updatePodcast(id: String) = repository.updatePodcast(id)
}
