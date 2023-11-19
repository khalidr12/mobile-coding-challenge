package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.PodcastEntity
import com.audiobooks.codingchallenge.database.PodcastsEntity
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PodcastDeserializer : JsonDeserializer<PodcastEntity> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PodcastEntity {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asString
        val image = jsonObject?.get("image")?.asString
        val title = jsonObject?.get("title")?.asString
        val publisher = jsonObject?.get("publisher")?.asString
        val thumbnail = jsonObject?.get("thumbnail")?.asString
        val description = jsonObject?.get("description")?.asString

        return PodcastEntity(
            id ?: "",
            image ?: "",
            title ?: "",
            publisher ?: "",
            thumbnail ?: "",
            description ?: ""
        )
    }
}
