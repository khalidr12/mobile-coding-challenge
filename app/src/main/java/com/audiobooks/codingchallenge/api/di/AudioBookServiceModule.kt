package com.audiobooks.codingchallenge.api.di

import com.audiobooks.codingchallenge.api.service.AudioBookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AudioBookServiceModule {

    private const val BASE_URL = "https://listen-api-test.listennotes.com/api/v2/"
    @Provides
    fun providesAudioBookService(): AudioBookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AudioBookService::class.java)

    }
}
