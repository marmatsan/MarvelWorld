package com.mango.marvelworld.data.remote

import com.google.gson.annotations.SerializedName

data class CharacterDataContainerDto(
    @SerializedName("offset")
    val offset: Long,
    @SerializedName("total")
    val total: Long,
    @SerializedName("results")
    val results: List<CharacterDto>,
)

data class CharacterDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto,
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("comics")
    val comics: ComicsDto,
    @SerializedName("series")
    val series: SeriesDto,
    @SerializedName("stories")
    val stories: StoriesDto,
    @SerializedName("events")
    val events: EventsDto,
    @SerializedName("urls")
    val urls: List<UrlDto>,
)

data class ThumbnailDto(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String,
)

data class ComicsDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<ComicSummaryDto>,
    @SerializedName("returned")
    val returned: Long,
)

data class ComicSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class SeriesDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<SeriesSummaryDto>,
    @SerializedName("returned")
    val returned: Long,
)

data class SeriesSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class StoriesDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<StorySummaryDto>,
    @SerializedName("returned")
    val returned: Long,
)

data class StorySummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
)

data class EventsDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<EventSummaryDto>,
    @SerializedName("returned")
    val returned: Long,
)

data class EventSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class UrlDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
)