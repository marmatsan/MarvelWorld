package com.mango.marvelworld.ui.presentation.characterlist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mango.marvelworld.R
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

@Composable
fun CharacterListItem(
    character: Character
) {
    val localContext = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 14.dp,
                bottom = 14.dp
            )
            .clickable { // TODO
                /*
                val intent = Intent(localContext, DetailActivity::class.java)
                    .putExtra("movieId", movie.id)
                localContext.startActivity(intent)
                 */
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CharacterImage(
            portraitUrl = character
                .thumbnail.path
                .plus("/portrait_xlarge")
                .plus(".")
                .plus(character.thumbnail.extension)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = character.name
            )
            Spacer(
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = "Aparece en ${character.comics.available} comics",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CharacterImage(
    portraitUrl: String,
    size: Dp = 100.dp
) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = portraitUrl,
            contentDescription = stringResource(R.string.cd_character_portrait)
        )
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
@Preview(name = "Dark Theme", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CharacterListItemPreview() {
    CharacterListItem(
        character = createMockCharacter()
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