package com.audiobooks.codingchallenge.api.di

import com.audiobooks.codingchallenge.api.repository.GetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import com.audiobooks.codingchallenge.api.service.AudioBookService
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
        service: AudioBookService
    ): IGetBestPodcastsRepository {
        return GetBestPodcastsRepository(service)
    }
}