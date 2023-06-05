package com.mango.marvelworld.ui.presentation.characterlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mango.marvelworld.R
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.mappers.toCharacterDataContainer
import com.mango.marvelworld.domain.Constants.Empty
import com.mango.marvelworld.domain.models.Character
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    queryText: String,
    onQueryTextChange: (String) -> Unit,
    active: Boolean,
    onSearchBarActive: (Boolean) -> Unit,
    cachedDataContainersState: List<CharacterDataContainerEntity>,
    onCharacterCachedItemClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .semantics { isContainer = true }
            .zIndex(1f)
            .fillMaxWidth()
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            query = queryText,
            onQueryChange = { newQueryText ->
                onQueryTextChange(newQueryText)
            },
            onSearch = {
                onSearchBarActive(false)
            },
            active = active,
            onActiveChange = { newActiveState ->
                onSearchBarActive(newActiveState)
            },
            placeholder = {
                Text(
                    text = if (!active)
                        stringResource(R.string.l_busca_un_personaje)
                    else
                        stringResource(R.string.l_busca_un_personaje_por_su_nombre)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.cd_search_icon)
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (queryText.isNotEmpty()) {
                                onQueryTextChange(String.Empty)
                            } else {
                                onSearchBarActive(false)
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.cd_close_icon)
                    )
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(cachedDataContainersState) { characterDataContainerEntity ->
                    characterDataContainerEntity
                        .toCharacterDataContainer()
                        .results
                        .filter { character ->
                            queryText.isNotEmpty() && character.name.contains(
                                queryText,
                                ignoreCase = true
                            )
                        }
                        .forEach { character ->
                            CharacterCachedItem(
                                character = character,
                                onCharacterCachedItemClick = onCharacterCachedItemClick
                            )
                        }
                }
            }
        }
    }
}

@Composable
fun CharacterCachedItem(
    character: Character,
    onCharacterCachedItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCharacterCachedItemClick(character.name)
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = 10.dp),
            imageVector = Icons.Default.History,
            contentDescription = stringResource(R.string.cd_history_icon)
        )
        Text(
            text = character.name
        )
    }
}

@Preview
@Composable
fun CharacterCachedItemPreview(
    character: Character = createMockCharacter()
) {
    CharacterCachedItem(
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