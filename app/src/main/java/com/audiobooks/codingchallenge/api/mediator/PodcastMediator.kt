package com.audiobooks.codingchallenge.api.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.audiobooks.codingchallenge.api.response.toEntity
import com.audiobooks.codingchallenge.api.service.AudioBookService
import com.audiobooks.codingchallenge.database.AppDatabase
import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.database.PodcastsEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PodcastMediator (
    private val database: AppDatabase,
    private val api: AudioBookService
) : RemoteMediator<Int, PodcastEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PodcastEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val entity = database.podcastsDao().getEntity("podcasts")
                    if (entity == null || !entity.hasNextPage){
                        return MediatorResult.Success(true)
                    }
                    entity.nextPageNumber
                }
            }
            val response =  api.getBestPodcasts(page = loadKey.toString())
            val podcasts = response.body()?.podcasts
            val hasNextPage = response.body()?.has_next
            database.withTransaction {
                val podcastEntities = podcasts?.map {
                    it.toEntity()
                }
                if (podcastEntities != null) {
                    database.podcastDao().insertPodcasts(podcastEntities)
                }
                if(response.body() != null){
                    database.podcastsDao().insertPodcast(
                        response.body()!!.toEntity()
                    )
                }
            }
            MediatorResult.Success(hasNextPage == false)
        } catch (e: IOException){
            MediatorResult.Error(e)
        } catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}