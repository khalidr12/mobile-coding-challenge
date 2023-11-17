package com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase

import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.usecase.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception


class GetBestPodcastsUseCase @Inject constructor(
    private val repository: IGetBestPodcastsRepository,
) {
    fun initialize() = flow {
        try {
            emit(Result.Loading)
            repository.initializeData()
            repository.loadBatchFromRoom()
            val apiResponse = repository.getAllPodcastsUntilOffset()
            if (apiResponse.isNotEmpty()) {
                emit(Result.Success(apiResponse))
            } else {
                emit(Result.Error(Exception("Api is unsuccessful")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun loadData() = flow {
        try {
            emit(Result.Loading)
            val apiResponse = repository.getAllPodcastsUntilOffset()
            if (apiResponse.isNotEmpty()) {
                emit(Result.Success(apiResponse))
            } else {
                emit(Result.Error(Exception("Api is unsuccessful")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun loadBatch() = flow {
        if(repository.isNotLastPage()){
            repository.loadBatchFromRoom()
            val apiResponse = repository.getAllPodcastsUntilOffset()
            if (apiResponse.isNotEmpty()) {
                emit(Result.Success(apiResponse))
            } else {
                emit(Result.Error(Exception("Api is unsuccessful")))
            }
        }
    }
}
