package com.mango.marvelworld.domain.models.characterlist.mapper

import com.mango.marvelworld.domain.models.characterlist.Character
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.domain.models.characterlist.ComicSummary
import com.mango.marvelworld.domain.models.characterlist.Comics
import com.mango.marvelworld.domain.models.characterlist.EventSummary
import com.mango.marvelworld.domain.models.characterlist.Events
import com.mango.marvelworld.domain.models.characterlist.Series
import com.mango.marvelworld.domain.models.characterlist.SeriesSummary
import com.mango.marvelworld.domain.models.characterlist.Stories
import com.mango.marvelworld.domain.models.characterlist.StorySummary
import com.mango.marvelworld.domain.models.characterlist.Thumbnail
import com.mango.marvelworld.domain.models.characterlist.Url
import com.mango.marvelworld.domain.models.characterlist.cache.CharacterDataContainerEntity
import com.mango.marvelworld.domain.models.characterlist.cache.CharacterEntity
import com.mango.marvelworld.domain.models.characterlist.cache.ComicSummaryEntity
import com.mango.marvelworld.domain.models.characterlist.cache.ComicsEntity
import com.mango.marvelworld.domain.models.characterlist.cache.EventSummaryEntity
import com.mango.marvelworld.domain.models.characterlist.cache.EventsEntity
import com.mango.marvelworld.domain.models.characterlist.cache.SeriesEntity
import com.mango.marvelworld.domain.models.characterlist.cache.SeriesSummaryEntity
import com.mango.marvelworld.domain.models.characterlist.cache.StoriesEntity
import com.mango.marvelworld.domain.models.characterlist.cache.StorySummaryEntity
import com.mango.marvelworld.domain.models.characterlist.cache.ThumbnailEntity
import com.mango.marvelworld.domain.models.characterlist.cache.UrlEntity

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