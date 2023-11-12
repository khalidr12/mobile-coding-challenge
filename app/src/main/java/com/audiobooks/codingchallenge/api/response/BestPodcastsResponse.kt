package com.audiobooks.codingchallenge.api.response

data class BestPodcastsResponse(
    val id: Int,
    val name: String,
    val total: Int,
    val hasNext: Boolean,
    val podcasts: List<Podcast>,
    val parentId: Int,
    val pageNumber: Int,
    val hasPrevious: Boolean,
    val listenNotesUrl: String,
    val nextPageNumber: Int,
    val previousPageNumber: Int
)

data class Podcast(
    val id: String,
    val rss: String,
    val type: String,
    val email: String,
    val extra: Extra,
    val image: String,
    val title: String,
    val country: String,
    val website: String,
    val language: String,
    val genreIds: List<Int>,
    val itunesId: Long,
    val publisher: String,
    val thumbnail: String,
    val isClaimed: Boolean,
    val description: String,
    val lookingFor: LookingFor,
    val listenScore: Int,
    val totalEpisodes: Int,
    val listenNotesUrl: String,
    val audioLengthSec: Int,
    val explicitContent: Boolean,
    val latestEpisodeId: String,
    val latestPubDateMs: Long,
    val earliestPubDateMs: Long,
    val updateFrequencyHours: Int,
    val listenScoreGlobalRank: String
)

data class Extra(
    val url1: String,
    val url2: String,
    val url3: String,
    val googleUrl: String,
    val spotifyUrl: String,
    val youtubeUrl: String,
    val linkedinUrl: String,
    val wechatHandle: String,
    val patreonHandle: String,
    val twitterHandle: String,
    val facebookHandle: String,
    val amazonMusicUrl: String,
    val instagramHandle: String
)

data class LookingFor(
    val guests: Boolean,
    val cohosts: Boolean,
    val sponsors: Boolean,
    val crossPromotion: Boolean
)

