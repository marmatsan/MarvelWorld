package com.mango.marvelworld.domain.models

data class CharacterDataContainer(
    val offset: Long,
    val total: Long,
    val results: List<Character>,
)

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceUri: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Url>,
)

data class Thumbnail(
    val path: String,
    val extension: String,
)

data class Comics(
    val available: Long,
    val collectionUri: String,
    val items: List<ComicSummary>,
    val returned: Long,
)

data class ComicSummary(
    val resourceUri: String,
    val name: String,
)

data class Series(
    val available: Long,
    val collectionUri: String,
    val items: List<SeriesSummary>,
    val returned: Long,
)

data class SeriesSummary(
    val resourceUri: String,
    val name: String,
)

data class Stories(
    val available: Long,
    val collectionUri: String,
    val items: List<StorySummary>,
    val returned: Long,
)

data class StorySummary(
    val resourceUri: String,
    val name: String,
    val type: String,
)

data class Events(
    val available: Long,
    val collectionUri: String,
    val items: List<EventSummary>,
    val returned: Long,
)

data class EventSummary(
    val resourceUri: String,
    val name: String,
)

data class Url(
    val type: String,
    val url: String,
)