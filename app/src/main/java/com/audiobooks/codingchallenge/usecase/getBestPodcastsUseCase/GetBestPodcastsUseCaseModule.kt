package com.audiobooks.codingchallenge.usecase.getBestPodcastsUseCase

import com.audiobooks.codingchallenge.api.repository.IGetBestPodcastsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GetBestPodcastsUseCaseModule {

    @Provides
    fun providesGetBestPodcastsUseCase(
        repository: IGetBestPodcastsRepository
    ) : GetBestPodcastsUseCase {
        return GetBestPodcastsUseCase(repository)
    }
}