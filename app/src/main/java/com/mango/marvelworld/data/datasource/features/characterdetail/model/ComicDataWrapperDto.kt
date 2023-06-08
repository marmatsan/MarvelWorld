package com.mango.marvelworld.data.datasource.features.characterdetail.model

import com.google.gson.annotations.SerializedName

data class ComicDataWrapperDto(
    @SerializedName("code")
    val code: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("attributionHTML")
    val attributionHtml: String,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("data")
    val data: ComicDataContainerDto,
)

data class ComicDataContainerDto(
    @SerializedName("offset")
    val offset: Long,
    @SerializedName("limit")
    val limit: Long,
    @SerializedName("total")
    val total: Long,
    @SerializedName("count")
    val count: Long,
    @SerializedName("results")
    val results: List<ComicDto>,
)

data class ComicDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("digitalId")
    val digitalId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("issueNumber")
    val issueNumber: Long,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("pageCount")
    val pageCount: Long,
    @SerializedName("textObjects")
    val textObjects: List<TextObjectDto>,
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("urls")
    val urls: List<UrlDto>,
    @SerializedName("series")
    val series: SeriesSummaryDto,
    @SerializedName("variants")
    val variants: List<ComicSummaryDto>,
    @SerializedName("collections")
    val collections: List<ComicSummaryDto>,
    @SerializedName("collectedIssues")
    val collectedIssues: List<ComicSummaryDto>,
    @SerializedName("dates")
    val dates: List<ComicDateDto>,
    @SerializedName("prices")
    val prices: List<ComicPriceDto>,
    @SerializedName("thumbnail")
    val thumbnail: ImageDto,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("creators")
    val creators: CreatorListDto,
    @SerializedName("characters")
    val characters: CharacterListDto,
    @SerializedName("stories")
    val stories: StoryListDto,
    @SerializedName("events")
    val events: EventListDto,
)

data class TextObjectDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("text")
    val text: String,
)

data class UrlDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
)

data class SeriesSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class ComicSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class ComicDateDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String,
)

data class ComicPriceDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: Double,
)

data class ImageDto(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String,
)

data class CreatorListDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("returned")
    val returned: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<CreatorSummaryDto>,
)

data class CreatorSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
)

data class CharacterListDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("returned")
    val returned: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<CharacterSummaryDto>,
)

data class CharacterSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)

data class StoryListDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("returned")
    val returned: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<StorySummaryDto>,
)

data class StorySummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
)

data class EventListDto(
    @SerializedName("available")
    val available: Long,
    @SerializedName("returned")
    val returned: Long,
    @SerializedName("collectionURI")
    val collectionUri: String,
    @SerializedName("items")
    val items: List<EventSummaryDto>,
)

data class EventSummaryDto(
    @SerializedName("resourceURI")
    val resourceUri: String,
    @SerializedName("name")
    val name: String,
)
