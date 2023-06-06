package com.mango.marvelworld.ui.presentation.features.searchappbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mango.marvelworld.domain.models.characterlist.Character
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

@Composable
fun CharacterSearchAppBarItem(
    character: Character,
    onCharacterCachedItemClick: (Character) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .wrapContentSize()
            .clickable {
                onCharacterCachedItemClick(character)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = character
                .thumbnail.path
                .plus("/portrait_xlarge")
                .plus(".")
                .plus(character.thumbnail.extension),
            contentDescription = "Character Image"
        )
        Text(
            text = character.name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCachedItemPreview(
    character: Character = createMockCharacter()
) {
    CharacterSearchAppBarItem(
        character = character,
        onCharacterCachedItemClick = {}
    )
}

fun createMockCharacter(): Character {
    val thumbnail = Thumbnail(
        path = "https://example.com/image",
        extension = "jpg"
    )

    val comics = Comics(
        available = 5,
        collectionUri = "https://example.com/collection",
        items = listOf(
            ComicSummary("https://example.com/comic1", "Comic 1"),
            ComicSummary("https://example.com/comic2", "Comic 2"),
            ComicSummary("https://example.com/comic3", "Comic 3")
        ),
        returned = 3
    )

    val series = Series(
        available = 3,
        collectionUri = "https://example.com/series",
        items = listOf(
            SeriesSummary("https://example.com/series1", "Series 1"),
            SeriesSummary("https://example.com/series2", "Series 2")
        ),
        returned = 2
    )

    val stories = Stories(
        available = 4,
        collectionUri = "https://example.com/stories",
        items = listOf(
            StorySummary("https://example.com/story1", "Story 1", "Type 1"),
            StorySummary("https://example.com/story2", "Story 2", "Type 2"),
            StorySummary("https://example.com/story3", "Story 3", "Type 1")
        ),
        returned = 3
    )

    val events = Events(
        available = 2,
        collectionUri = "https://example.com/events",
        items = listOf(
            EventSummary("https://example.com/event1", "Event 1"),
            EventSummary("https://example.com/event2", "Event 2")
        ),
        returned = 2
    )

    val urls = listOf(
        Url("detail", "https://example.com/detail"),
        Url("wiki", "https://example.com/wiki")
    )

    return Character(
        id = 1,
        name = "Character Name",
        description = "Character Description",
        modified = "2023-05-24",
        thumbnail = thumbnail,
        resourceUri = "https://example.com/character/1",
        comics = comics,
        series = series,
        stories = stories,
        events = events,
        urls = urls
    )
}