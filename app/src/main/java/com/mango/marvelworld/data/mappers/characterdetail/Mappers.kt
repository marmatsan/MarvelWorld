package com.mango.marvelworld.data.mappers.characterdetail

import com.mango.marvelworld.data.remote.characterdetail.CharacterListDto
import com.mango.marvelworld.data.remote.characterdetail.CharacterSummaryDto
import com.mango.marvelworld.data.remote.characterdetail.ComicDataContainerDto
import com.mango.marvelworld.data.remote.characterdetail.ComicDataWrapperDto
import com.mango.marvelworld.data.remote.characterdetail.ComicDateDto
import com.mango.marvelworld.data.remote.characterdetail.ComicDto
import com.mango.marvelworld.data.remote.characterdetail.ComicPriceDto
import com.mango.marvelworld.data.remote.characterdetail.ComicSummaryDto
import com.mango.marvelworld.data.remote.characterdetail.CreatorListDto
import com.mango.marvelworld.data.remote.characterdetail.CreatorSummaryDto
import com.mango.marvelworld.data.remote.characterdetail.EventListDto
import com.mango.marvelworld.data.remote.characterdetail.EventSummaryDto
import com.mango.marvelworld.data.remote.characterdetail.ImageDto
import com.mango.marvelworld.data.remote.characterdetail.SeriesSummaryDto
import com.mango.marvelworld.data.remote.characterdetail.StoryListDto
import com.mango.marvelworld.data.remote.characterdetail.StorySummaryDto
import com.mango.marvelworld.data.remote.characterdetail.TextObjectDto
import com.mango.marvelworld.data.remote.characterdetail.UrlDto
import com.mango.marvelworld.domain.models.characterdetail.CharacterList
import com.mango.marvelworld.domain.models.characterdetail.CharacterSummary
import com.mango.marvelworld.domain.models.characterdetail.Comic
import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterdetail.ComicDataWrapper
import com.mango.marvelworld.domain.models.characterdetail.ComicDate
import com.mango.marvelworld.domain.models.characterdetail.ComicPrice
import com.mango.marvelworld.domain.models.characterdetail.ComicSummary
import com.mango.marvelworld.domain.models.characterdetail.CreatorList
import com.mango.marvelworld.domain.models.characterdetail.CreatorSummary
import com.mango.marvelworld.domain.models.characterdetail.EventList
import com.mango.marvelworld.domain.models.characterdetail.EventSummary
import com.mango.marvelworld.domain.models.characterdetail.Image
import com.mango.marvelworld.domain.models.characterdetail.SeriesSummary
import com.mango.marvelworld.domain.models.characterdetail.StoryList
import com.mango.marvelworld.domain.models.characterdetail.StorySummary
import com.mango.marvelworld.domain.models.characterdetail.TextObject
import com.mango.marvelworld.domain.models.characterdetail.Url

fun ComicDataWrapperDto.toComicDataWrapper(): ComicDataWrapper {
    return ComicDataWrapper(
        code = code,
        status = status,
        copyright = copyright,
        attributionText = attributionText,
        attributionHtml = attributionHtml,
        etag = etag,
        data = data.toComicDataContainer()
    )
}

fun ComicDataContainerDto.toComicDataContainer(): ComicDataContainer {
    return ComicDataContainer(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = results.map { it.toComic() }
    )
}

fun ComicDto.toComic(): Comic {
    return Comic(
        id = id,
        digitalId = digitalId,
        title = title,
        issueNumber = issueNumber,
        variantDescription = variantDescription,
        description = description,
        modified = modified,
        isbn = isbn,
        upc = upc,
        diamondCode = diamondCode,
        ean = ean,
        issn = issn,
        format = format,
        pageCount = pageCount,
        textObjects = textObjects.map { it.toTextObject() },
        resourceUri = resourceUri,
        urls = urls.map { it.toUrl() },
        series = series.toSeriesSummary(),
        variants = variants.map { it.toComicSummary() },
        collections = collections.map { it.toComicSummary() },
        collectedIssues = collectedIssues.map { it.toComicSummary() },
        dates = dates.map { it.toComicDate() },
        prices = prices.map { it.toComicPrice() },
        thumbnail = thumbnail.toImage(),
        images = images.map { it.toImage() },
        creators = creators.toCreatorList(),
        characters = characters.toCharacterDetail(),
        stories = stories.toStoryList(),
        events = events.toEventList()
    )
}

fun TextObjectDto.toTextObject(): TextObject {
    return TextObject(
        type = type,
        language = language,
        text = text
    )
}

fun UrlDto.toUrl(): Url {
    return Url(
        type = type,
        url = url
    )
}

fun SeriesSummaryDto.toSeriesSummary(): SeriesSummary {
    return SeriesSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun ComicSummaryDto.toComicSummary(): ComicSummary {
    return ComicSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun ComicDateDto.toComicDate(): ComicDate {
    return ComicDate(
        type = type,
        date = date
    )
}

fun ComicPriceDto.toComicPrice(): ComicPrice {
    return ComicPrice(
        type = type,
        price = price
    )
}

fun ImageDto.toImage(): Image {
    return Image(
        path = path,
        extension = extension
    )
}

fun CreatorListDto.toCreatorList(): CreatorList {
    return CreatorList(
        available = available,
        returned = returned,
        collectionUri = collectionUri,
        items = items.map { it.toCreatorSummary() }
    )
}

fun CreatorSummaryDto.toCreatorSummary(): CreatorSummary {
    return CreatorSummary(
        resourceUri = resourceUri,
        name = name,
        role = role
    )
}

fun CharacterListDto.toCharacterDetail(): CharacterList {
    return CharacterList(
        available = available,
        returned = returned,
        collectionUri = collectionUri,
        items = items.map { it.toCharacterSummary() }
    )
}

fun CharacterSummaryDto.toCharacterSummary(): CharacterSummary {
    return CharacterSummary(
        resourceUri = resourceUri,
        name = name
    )
}

fun StoryListDto.toStoryList(): StoryList {
    return StoryList(
        available = available,
        returned = returned,
        collectionUri = collectionUri,
        items = items.map { it.toStorySummary() }
    )
}

fun StorySummaryDto.toStorySummary(): StorySummary {
    return StorySummary(
        resourceUri = resourceUri,
        name = name,
        type = type
    )
}

fun EventListDto.toEventList(): EventList {
    return EventList(
        available = available,
        returned = returned,
        collectionUri = collectionUri,
        items = items.map { it.toEventSummary() }
    )
}

fun EventSummaryDto.toEventSummary(): EventSummary {
    return EventSummary(
        resourceUri = resourceUri,
        name = name
    )
}