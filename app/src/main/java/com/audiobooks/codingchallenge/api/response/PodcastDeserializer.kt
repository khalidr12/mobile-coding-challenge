package com.audiobooks.codingchallenge.api.response

import com.audiobooks.codingchallenge.database.Podcast
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PodcastDeserializer : JsonDeserializer<Podcast> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Podcast {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asString
        val image = jsonObject?.get("image")?.asString
        val title = jsonObject?.get("title")?.asString
        val publisher = jsonObject?.get("publisher")?.asString
        val thumbnail = jsonObject?.get("thumbnail")?.asString
        val description = jsonObject?.get("description")?.asString

        return Podcast(
            id ?: "",
            image ?: "",
            title ?: "",
            publisher ?: "",
            thumbnail ?: "",
            description ?: ""
        )
    }
}
