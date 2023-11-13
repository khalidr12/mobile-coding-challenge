package com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase

import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.response.BestPodcastsResponse
import com.audiobooks.codingchallenge.api.response.toBestPodcastsModel
import com.audiobooks.codingchallenge.usecase.Result
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeoutException
import javax.inject.Inject


class GetBestPodcastsUseCase @Inject constructor(
    private val repository: IGetBestPodcastsRepository,
) {
    operator fun invoke() = flow {
        try {
            emit(Result.Loading)
            val apiResponse = repository.getBestPodcasts()
            if (apiResponse.isSuccessful) {
                val result = apiResponse.body() as BestPodcastsResponse
                emit(Result.Success(result.toBestPodcastsModel()))
            } else {
                emit(Result.Error(Exception("Api is unsuccessful")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
