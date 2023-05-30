package com.mango.marvelworld.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mango.marvelworld.domain.Constants

@Entity(
    tableName = Constants.Room.DATABASE_NAME
)
@TypeConverters(CharactersEntityConverter::class)
data class CharacterDataContainerEntity(
    @PrimaryKey
    val offset: Long,
    val total: Long,
    val results: List<CharacterEntity>,
)

data class CharacterEntity(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailEntity,
    val resourceUri: String,
    val comics: ComicsEntity,
    val series: SeriesEntity,
    val stories: StoriesEntity,
    val events: EventsEntity,
    val urls: List<UrlEntity>,
)

data class ThumbnailEntity(
    val path: String,
    val extension: String,
)

data class ComicsEntity(
    val available: Long,
    val collectionUri: String,
    val items: List<ComicSummaryEntity>,
    val returned: Long,
)

data class ComicSummaryEntity(
    val resourceUri: String,
    val name: String,
)

data class SeriesEntity(
    val available: Long,
    val collectionUri: String,
    val items: List<SeriesSummaryEntity>,
    val returned: Long,
)

data class SeriesSummaryEntity(
    val resourceUri: String,
    val name: String,
)

data class StoriesEntity(
    val available: Long,
    val collectionUri: String,
    val items: List<StorySummaryEntity>,
    val returned: Long,
)

data class StorySummaryEntity(
    val resourceUri: String,
    val name: String,
    val type: String,
)

data class EventsEntity(
    val available: Long,
    val collectionUri: String,
    val items: List<EventSummaryEntity>,
    val returned: Long,
)

data class EventSummaryEntity(
    val resourceUri: String,
    val name: String,
)

data class UrlEntity(
    val type: String,
    val url: String,
)