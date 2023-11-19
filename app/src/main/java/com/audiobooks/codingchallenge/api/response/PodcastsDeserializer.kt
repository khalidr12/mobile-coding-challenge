package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.database.PodcastsEntity
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PodcastsDeserializer: JsonDeserializer<PodcastsEntity> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PodcastsEntity {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asString
        val hasNextPage =  jsonObject?.get("has_next")?.asBoolean
        val nextPageNumber = jsonObject?.get("next_page_number")?.asInt

        return PodcastsEntity(
            id ?: "",
            hasNextPage ?: false,
            nextPageNumber ?: 0
        )
    }
}