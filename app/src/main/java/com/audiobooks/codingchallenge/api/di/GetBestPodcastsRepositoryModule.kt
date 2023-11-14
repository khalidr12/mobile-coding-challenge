package com.audiobooks.codingchallenge.api.di

import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.service.AudioBookService
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
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
        service: AudioBookService,
        podcastsDAO: BestPodcastsDAO
    ): IGetBestPodcastsRepository {
        return GetBestPodcastsRepository(service, podcastsDAO)
    }
}