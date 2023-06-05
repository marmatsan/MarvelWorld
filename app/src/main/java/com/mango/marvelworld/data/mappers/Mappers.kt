package com.mango.marvelworld.data.mappers

import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.local.CharacterEntity
import com.mango.marvelworld.data.local.ComicSummaryEntity
import com.mango.marvelworld.data.local.ComicsEntity
import com.mango.marvelworld.data.local.EventSummaryEntity
import com.mango.marvelworld.data.local.EventsEntity
import com.mango.marvelworld.data.local.SeriesEntity
import com.mango.marvelworld.data.local.SeriesSummaryEntity
import com.mango.marvelworld.data.local.StoriesEntity
import com.mango.marvelworld.data.local.StorySummaryEntity
import com.mango.marvelworld.data.local.ThumbnailEntity
import com.mango.marvelworld.data.local.UrlEntity
import com.mango.marvelworld.data.remote.characterlist.CharacterDataContainerDto
import com.mango.marvelworld.data.remote.characterlist.CharacterDto
import com.mango.marvelworld.data.remote.characterlist.ComicSummaryDto
import com.mango.marvelworld.data.remote.characterlist.ComicsDto
import com.mango.marvelworld.data.remote.characterlist.EventSummaryDto
import com.mango.marvelworld.data.remote.characterlist.EventsDto
import com.mango.marvelworld.data.remote.characterlist.SeriesDto
import com.mango.marvelworld.data.remote.characterlist.SeriesSummaryDto
import com.mango.marvelworld.data.remote.characterlist.StoriesDto
import com.mango.marvelworld.data.remote.characterlist.StorySummaryDto
import com.mango.marvelworld.data.remote.characterlist.ThumbnailDto
import com.mango.marvelworld.data.remote.characterlist.UrlDto
import com.mango.marvelworld.domain.models.Character
import com.mango.marvelworld.domain.models.CharacterDataContainer
import com.mango.marvelworld.domain.models.ComicSummary
import com.mango.marvelworld.domain.models.Comics
import com.mango.marvelworld.domain.models.EventSummary
import com.mango.marvelworld.domain.models.Events
import com.mango.marvelworld.domain.models.Series
import com.mango.marvelworld.domain.models.SeriesSummary
import com.mango.marvelworld.domain.models.Stories
import com.mango.marvelworld.domain.models.StorySummary
import com.mango.marvelworld.domain.models.Thumbnail
import com.mango.marvelworld.domain.models.Url

// Extension functions to map CharacterDataContainerDto to CharacterDataContainerEntity

fun CharacterDataContainerDto.toEntity(): CharacterDataContainerEntity {
    return CharacterDataContainerEntity(
        offset = offset,
        total = total,
        results = results.map { it.toEntity() }
    )
}

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail.toEntity(),
        resourceUri = resourceUri,
        comics = comics.toEntity(),
        series = series.toEntity(),
        stories = stories.toEntity(),
        events = events.toEntity(),
        urls = urls.map { it.toEntity() }
    )
}

fun ThumbnailDto.toEntity(): ThumbnailEntity {
    return ThumbnailEntity(
        path = path,
        extension = extension
    )
}

fun ComicsDto.toEntity(): ComicsEntity {
    return ComicsEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEntity() },
        returned = returned
    )
}

fun ComicSummaryDto.toEntity(): ComicSummaryEntity {
    return ComicSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

fun SeriesDto.toEntity(): SeriesEntity {
    return SeriesEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEntity() },
        returned = returned
    )
}

fun SeriesSummaryDto.toEntity(): SeriesSummaryEntity {
    return SeriesSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

fun StoriesDto.toEntity(): StoriesEntity {
    return StoriesEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEntity() },
        returned = returned
    )
}

fun StorySummaryDto.toEntity(): StorySummaryEntity {
    return StorySummaryEntity(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

fun EventsDto.toEntity(): EventsEntity {
    return EventsEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEntity() },
        returned = returned
    )
}

fun EventSummaryDto.toEntity(): EventSummaryEntity {
    return EventSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

fun UrlDto.toEntity(): UrlEntity {
    return UrlEntity(
        type = type,
        url = url
    )
}

// Extension functions to map CharacterDataContainerEntity to CharacterDataContainer

fun CharacterDataContainerEntity.toCharacterDataContainer(): CharacterDataContainer {
    return CharacterDataContainer(
        offset = offset,
        total = total,
        results = results.map { it.toCharacter() }
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail.toThumbnail(),
        resourceUri = resourceUri,
        comics = comics.toComics(),
        series = series.toSeries(),
        stories = stories.toStories(),
        events = events.toEvents(),
        urls = urls.map { it.toUrl() }
    )
}

fun ThumbnailEntity.toThumbnail(): Thumbnail {
    return Thumbnail(
        path = path,
        extension = extension
    )
}

fun ComicsEntity.toComics(): Comics {
    return Comics(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toComicSummary() },
        returned = returned
    )
}

fun ComicSummaryEntity.toComicSummary(): ComicSummary {
    return ComicSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun SeriesEntity.toSeries(): Series {
    return Series(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toSeriesSummary() },
        returned = returned
    )
}

fun SeriesSummaryEntity.toSeriesSummary(): SeriesSummary {
    return SeriesSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun StoriesEntity.toStories(): Stories {
    return Stories(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toStorySummary() },
        returned = returned
    )
}

fun StorySummaryEntity.toStorySummary(): StorySummary {
    return StorySummary(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

fun EventsEntity.toEvents(): Events {
    return Events(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEventSummary() },
        returned = returned
    )
}

fun EventSummaryEntity.toEventSummary(): EventSummary {
    return EventSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun UrlEntity.toUrl(): Url {
    return Url(
        type = type,
        url = url
    )
}

// Extension functions to map CharacterDataContainerDto to CharacterDataContainer

fun CharacterDataContainerDto.toCharacterDataContainer(): CharacterDataContainer {
    return CharacterDataContainer(
        offset = offset,
        total = total,
        results = results.map { it.toCharacter() }
    )
}

private fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail.toThumbnail(),
        resourceUri = resourceUri,
        comics = comics.toComics(),
        series = series.toSeries(),
        stories = stories.toStories(),
        events = events.toEvents(),
        urls = urls.map { it.toUrl() }
    )
}

private fun ThumbnailDto.toThumbnail(): Thumbnail {
    return Thumbnail(
        path = path,
        extension = extension
    )
}

private fun ComicsDto.toComics(): Comics {
    return Comics(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toComicSummary() },
        returned = returned
    )
}

private fun ComicSummaryDto.toComicSummary(): ComicSummary {
    return ComicSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun SeriesDto.toSeries(): Series {
    return Series(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toSeriesSummary() },
        returned = returned
    )
}

private fun SeriesSummaryDto.toSeriesSummary(): SeriesSummary {
    return SeriesSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun StoriesDto.toStories(): Stories {
    return Stories(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toStorySummary() },
        returned = returned
    )
}

private fun StorySummaryDto.toStorySummary(): StorySummary {
    return StorySummary(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

private fun EventsDto.toEvents(): Events {
    return Events(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEventSummary() },
        returned = returned
    )
}

private fun EventSummaryDto.toEventSummary(): EventSummary {
    return EventSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun UrlDto.toUrl(): Url {
    return Url(
        type = type,
        url = url
    )
}

// Extension functions to map CharacterDataContainer to CharacterDataContainerEntity

fun CharacterDataContainer.toCharacterDataContainerEntity(): CharacterDataContainerEntity {
    return CharacterDataContainerEntity(
        offset = offset,
        total = total,
        results = results.map { it.toCharacterEntity() }
    )
}

private fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail.toThumbnailEntity(),
        resourceUri = resourceUri,
        comics = comics.toComicsEntity(),
        series = series.toSeriesEntity(),
        stories = stories.toStoriesEntity(),
        events = events.toEventsEntity(),
        urls = urls.map { it.toUrlEntity() }
    )
}

private fun Thumbnail.toThumbnailEntity(): ThumbnailEntity {
    return ThumbnailEntity(
        path = path,
        extension = extension
    )
}

private fun Comics.toComicsEntity(): ComicsEntity {
    return ComicsEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toComicSummaryEntity() },
        returned = returned
    )
}

private fun ComicSummary.toComicSummaryEntity(): ComicSummaryEntity {
    return ComicSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

private fun Series.toSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toSeriesSummaryEntity() },
        returned = returned
    )
}

private fun SeriesSummary.toSeriesSummaryEntity(): SeriesSummaryEntity {
    return SeriesSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

private fun Stories.toStoriesEntity(): StoriesEntity {
    return StoriesEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toStorySummaryEntity() },
        returned = returned
    )
}

private fun StorySummary.toStorySummaryEntity(): StorySummaryEntity {
    return StorySummaryEntity(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

private fun Events.toEventsEntity(): EventsEntity {
    return EventsEntity(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEventSummaryEntity() },
        returned = returned
    )
}

private fun EventSummary.toEventSummaryEntity(): EventSummaryEntity {
    return EventSummaryEntity(
        resourceUri = resourceUri,
        name = name
    )
}

private fun Url.toUrlEntity(): UrlEntity {
    return UrlEntity(
        type = type,
        url = url
    )
}






