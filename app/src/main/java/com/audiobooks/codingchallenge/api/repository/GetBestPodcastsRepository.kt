package com.audiobooks.codingchallenge.api.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.PodcastEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBestPodcastsRepository @Inject constructor(
    private val podcastsDAO: BestPodcastsDAO,
    private val pager: Pager<Int, PodcastEntity>
) : IGetBestPodcastsRepository {
    override suspend fun getPodcastById(podcastId: String) : PodcastEntity? {
        return podcastsDAO.getPodcastById(podcastId = podcastId)
    }

    override suspend fun updatePodcast(podcastId: String): PodcastEntity? {
        val podcast = getPodcastById(podcastId)
        if (podcast != null) {
            podcastsDAO.update(podcast.copy(isFavourite = !podcast.isFavourite))
        }
        return podcast
    }

    override fun getPodcasts(): Flow<PagingData<PodcastEntity>> {
        return pager.flow.map { pagingData ->
            pagingData.map {
                PodcastEntity(
                    it.id,
                    it.image,
                    it.title,
                    it.publisher,
                    it.thumbnail,
                    it.description,
                    it.isFavourite
                )
            }
        }
    }
}