package com.mango.marvelworld.domain.models.characterlist.cache.mapper

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

fun CharacterDataContainerEntity.toCharacterDataContainer(): CharacterDataContainer {
    return CharacterDataContainer(
        offset = offset,
        total = total,
        results = results.map { it.toCharacter() }
    )
}

private fun CharacterEntity.toCharacter(): Character {
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

private fun ThumbnailEntity.toThumbnail(): Thumbnail {
    return Thumbnail(
        path = path,
        extension = extension
    )
}

private fun ComicsEntity.toComics(): Comics {
    return Comics(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toComicSummary() },
        returned = returned
    )
}

private fun ComicSummaryEntity.toComicSummary(): ComicSummary {
    return ComicSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun SeriesEntity.toSeries(): Series {
    return Series(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toSeriesSummary() },
        returned = returned
    )
}

private fun SeriesSummaryEntity.toSeriesSummary(): SeriesSummary {
    return SeriesSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun StoriesEntity.toStories(): Stories {
    return Stories(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toStorySummary() },
        returned = returned
    )
}

private fun StorySummaryEntity.toStorySummary(): StorySummary {
    return StorySummary(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

private fun EventsEntity.toEvents(): Events {
    return Events(
        available = available,
        collectionUri = collectionUri,
        items = items.map { it.toEventSummary() },
        returned = returned
    )
}

private fun EventSummaryEntity.toEventSummary(): EventSummary {
    return EventSummary(
        resourceUri = resourceUri,
        name = name
    )
}

private fun UrlEntity.toUrl(): Url {
    return Url(
        type = type,
        url = url
    )
}