package com.mango.marvelworld.data.datasource.features.characterlist.remote.mapper

import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDataContainerDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.ComicListDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.ComicSummaryDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.EventListDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.EventSummaryDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.ImageDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.SeriesListDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.SeriesSummaryDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.StoryListDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.StorySummaryDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.UrlDto
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

private fun ImageDto.toThumbnail(): Thumbnail {
    return Thumbnail(
        path = path,
        extension = extension
    )
}

private fun ComicListDto.toComics(): Comics {
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

private fun SeriesListDto.toSeries(): Series {
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

private fun StoryListDto.toStories(): Stories {
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

private fun EventListDto.toEvents(): Events {
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




