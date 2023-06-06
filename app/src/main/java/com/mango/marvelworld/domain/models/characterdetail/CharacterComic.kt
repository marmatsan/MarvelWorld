package com.mango.marvelworld.domain.models.characterdetail

data class ComicDataWrapper(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHtml: String,
    val etag: String,
    val data: ComicDataContainer,
)

data class ComicDataContainer(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Comic>,
)

data class Comic(
    val id: Long,
    val digitalId: Long,
    val title: String,
    val issueNumber: Long,
    val variantDescription: String,
    val description: String?,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Long,
    val textObjects: List<TextObject>,
    val resourceUri: String,
    val urls: List<Url>,
    val series: SeriesSummary,
    val variants: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val collectedIssues: List<ComicSummary>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: Image,
    val images: List<Image>,
    val creators: CreatorList,
    val characters: CharacterList,
    val stories: StoryList,
    val events: EventList,
)

data class TextObject(
    val type: String,
    val language: String,
    val text: String,
)

data class Url(
    val type: String,
    val url: String,
)

data class SeriesSummary(
    val resourceUri: String,
    val name: String,
)

data class ComicSummary(
    val resourceUri: String,
    val name: String,
)

data class ComicDate(
    val type: String,
    val date: String,
)

data class ComicPrice(
    val type: String,
    val price: Double,
)

data class Image(
    val path: String,
    val extension: String,
)

data class CreatorList(
    val available: Long,
    val returned: Long,
    val collectionUri: String,
    val items: List<CreatorSummary>,
)

data class CreatorSummary(
    val resourceUri: String,
    val name: String,
    val role: String,
)

data class CharacterList(
    val available: Long,
    val returned: Long,
    val collectionUri: String,
    val items: List<CharacterSummary>,
)

data class CharacterSummary(
    val resourceUri: String,
    val name: String,
)

data class StoryList(
    val available: Long,
    val returned: Long,
    val collectionUri: String,
    val items: List<StorySummary>,
)

data class StorySummary(
    val resourceUri: String,
    val name: String,
    val type: String,
)

data class EventList(
    val available: Long,
    val returned: Long,
    val collectionUri: String,
    val items: List<EventSummary>,
)

data class EventSummary(
    val resourceUri: String,
    val name: String,
)
