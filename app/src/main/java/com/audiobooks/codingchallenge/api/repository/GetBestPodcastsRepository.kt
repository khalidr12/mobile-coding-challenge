package com.audiobooks.codingchallenge.api.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.audiobooks.codingchallenge.api.response.Podcast
import com.audiobooks.codingchallenge.api.response.toEntity
import com.audiobooks.codingchallenge.api.response.toPodcast
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.PodcastEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBestPodcastsRepository @Inject constructor(
    private val podcastsDAO: BestPodcastsDAO,
    private val pager: Pager<Int, PodcastEntity>
) : IGetBestPodcastsRepository {
    override suspend fun getPodcastById(podcastId: String) : Flow<Podcast?> {
        return flow {
            try {
                val podcast = podcastsDAO.getPodcastById(podcastId = podcastId)
                if(podcast != null) {
                    emit(podcast.toPodcast())
                }
            } catch (e: Exception){
                Log.ERROR
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updatePodcast(podcastId: String) {
        return getPodcastById(podcastId).collect { podcast ->
            if(podcast != null){
                podcastsDAO.update(podcast.copy(isFavourite = !podcast.isFavourite).toEntity())
            }
        }
    }

    override fun getPodcasts(): Flow<PagingData<Podcast>> {
        return pager.flow.map { pagingData ->
            pagingData.map {
               it.toPodcast()
            }
        }
    }
}