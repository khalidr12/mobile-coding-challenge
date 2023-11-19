package com.audiobooks.codingchallenge.api.di

import androidx.paging.Pager
import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.PodcastEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetBestPodcastsRepositoryModule {

    @Provides
    @Singleton
    fun providesGetBestPodcastsRepositoryModule(
        podcastsDAO: BestPodcastsDAO,
        pager: Pager<Int, PodcastEntity>
    ): IGetBestPodcastsRepository {
        return GetBestPodcastsRepository(podcastsDAO, pager)
    }
}