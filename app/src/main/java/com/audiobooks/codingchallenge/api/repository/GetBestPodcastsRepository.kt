package com.audiobooks.codingchallenge.api.repository

import com.audiobooks.codingchallenge.api.service.AudioBookService
import com.audiobooks.codingchallenge.database.BestPodcastsDAO
import com.audiobooks.codingchallenge.database.Podcast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBestPodcastsRepository @Inject constructor(
    private val audioBookService: AudioBookService,
    private val podcastsDAO: BestPodcastsDAO
) : IGetBestPodcastsRepository {

    private val batchSize = 10
    private var offset = 0
    private var page = 1
    private var hasNext = true

    override suspend fun initializeData() {
        // Get initial existing podcasts from the data base
        val podcastsFromDB = getAllPodcastsUntilOffset()

        // If the db is empty, then we need to get a new batch from the api
        if (podcastsFromDB.isEmpty()) {
            val podcastsFromApi = getBestPodcastsFromApi()
            podcastsDAO.insertPodcasts(podcastsFromApi)
        } else {
            // otherwise we need to refresh the data and add the newly added podcasts to
            // our existing copy in the data base so it is up to date
            refreshData(podcastsFromDB)
        }
    }

    override suspend fun isNotLastPage(): Boolean {
        return hasNext
    }

    private suspend fun refreshData(podcastsFromDB : List<Podcast>) {
        val latestPodcastsFromApi = getBestPodcastsFromApi()

        // loop through every new podcast, and insert/update all podcasts that have not
        // been favourited since that means they can be a new copy
        for (apiPodcast in latestPodcastsFromApi) {
            val existingPodcast = podcastsFromDB.find { it.id == apiPodcast.id }

            if (existingPodcast != null) {
                if (!existingPodcast.isFavourite) {
                    podcastsDAO.update(apiPodcast)
                }
            } else {
                podcastsDAO.insert(apiPodcast)
            }
        }
    }

    private suspend fun addNextPage(){
        val podcasts = getBestPodcastsFromApi()
        for (podcast in podcasts) {
            podcastsDAO.insert(podcast)
        }
    }

    // Get the next amount of podcasts, up to the offset
    override suspend fun getAllPodcastsUntilOffset() : List<Podcast> {
        return podcastsDAO.getAllPodcasts(offset)
    }
    override suspend fun loadBatchFromRoom(): List<Podcast> {
        // the api returns 20 podcasts at a time, so we know we are at the end of the page when
        // we have an offset that is perfectly divisible by 20 (such as 20, 40, 60)
        // this means we need to add the next page to the db
        if(offset % 20 == 0) {
            addNextPage()
        }

        // get the next X podcasts
        val paginatedPodcasts = podcastsDAO.getPaginatedPodcasts(batchSize, offset)
        offset += batchSize
        return paginatedPodcasts
    }
    suspend fun getPodcastById(podcastId: String) : Podcast? {
        return podcastsDAO.getPodcastById(podcastId = podcastId)
    }

    suspend fun updatePodcast(podcastId: String): Podcast? {
        val podcast = getPodcastById(podcastId)
        if (podcast != null) {
            podcastsDAO.update(podcast.copy(isFavourite = !podcast.isFavourite))
        }
        return podcast
    }

    override suspend fun getBestPodcastsFromApi(): List<Podcast> {
        // to limit the amount of requests sent to the backend, we need to check if the response
        // has a next page to limit infinite requests in pagination
        if(hasNext) {
            val response =  audioBookService.getBestPodcasts(page = page.toString()).body()
            if(response != null && response.has_next){
                page += 1
                return response.podcasts
            } else {
                hasNext = false
            }
        }
        return emptyList()
    }
}