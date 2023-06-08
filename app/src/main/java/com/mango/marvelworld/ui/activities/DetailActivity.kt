package com.mango.marvelworld.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.load
import com.mango.marvelworld.R
import com.mango.marvelworld.databinding.ActivityDetailBinding
import com.mango.marvelworld.domain.models.characterdetail.CharacterList
import com.mango.marvelworld.domain.models.characterdetail.Comic
import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterdetail.CreatorList
import com.mango.marvelworld.domain.models.characterdetail.EventList
import com.mango.marvelworld.domain.models.characterdetail.Image
import com.mango.marvelworld.domain.models.characterdetail.SeriesSummary
import com.mango.marvelworld.domain.models.characterdetail.StoryList
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.ui.presentation.features.characterdetail.DetailViewModel
import com.mango.marvelworld.ui.util.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val characterDetailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clicked character ID
        val characterId = intent.extras?.getLong("characterId")!!

        // Fetch character data (details and comics)
        characterDetailViewModel.fetchCharacterById(characterId = characterId)
        characterDetailViewModel.fetchCharacterComics(characterId = characterId)

        binding.viewCompose.setContent {
            val requestCharacterComicsState by characterDetailViewModel.requestCharacterComicsState.collectAsStateWithLifecycle()
            ComposeView(
                requestCharacterComicsState = requestCharacterComicsState
            )
        }

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                characterDetailViewModel.requestCharacterDetailsState.collectLatest { requestState ->
                    withContext(Dispatchers.Main) {
                        if (requestState is RequestState.Loading) {
                            binding.txtCharacterName.text = getString(R.string.l_cargando)
                        } else {
                            val characterDataContainer: CharacterDataContainer =
                                (requestState as RequestState.Success<*>).data as CharacterDataContainer
                            val character = characterDataContainer.results[0]

                            // Character Image
                            binding.imgCharacterPortrait.load(
                                data = character
                                    .thumbnail.path
                                    .plus("/portrait_xlarge")
                                    .plus(".")
                                    .plus(character.thumbnail.extension)
                            )

                            // Character Name
                            binding.txtCharacterName.text = character.name

                            // Character Description
                            binding.txtCharacterDescription.text =
                                character.description.ifEmpty { "Descripción no disponible" }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ComposeView(
        requestCharacterComicsState: RequestState<*>
    ) {
        if (requestCharacterComicsState is RequestState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val comicDataContainer: ComicDataContainer =
                    (requestCharacterComicsState as RequestState.Success<*>).data as ComicDataContainer
                CharacterComicsList(
                    comicList = comicDataContainer.results
                )
            }
        }
    }

    @Composable
    fun CharacterComicsList(
        comicList: List<Comic>
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(comicList) { comic ->
                CharacterComicItem(
                    comic = comic
                )
            }
        }
    }

    @Composable
    fun CharacterComicItem(
        comic: Comic
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 14.dp,
                    bottom = 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ComicImage(
                comicUrl = comic.thumbnail.path.plus(".").plus(comic.thumbnail.extension)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                val comicTitle = comic.title

                val comicSaleDate = comic.dates.find { comicDate ->
                    comicDate.type == "onsaleDate"
                }?.date

                val formattedDate = comicSaleDate?.let {
                    OffsetDateTime.parse(
                        it,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
                    )
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                } ?: "No disponible"

                Text(
                    text = comicTitle
                )
                Spacer(
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Fecha de publicación: $formattedDate",
                    color = Color.Gray
                )
            }
        }
    }

    @Composable
    fun ComicImage(
        comicUrl: String,
        size: Dp = 100.dp
    ) {
        Box(
            modifier = Modifier.size(size),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = comicUrl,
                contentDescription = stringResource(R.string.cd_comic_image)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CharacterComicItemPreview() {
        CharacterComicItem(
            comic = Comic(
                id = 0,
                digitalId = 0,
                title = "Example Comic",
                issueNumber = 0,
                variantDescription = "",
                description = null,
                modified = "",
                isbn = "",
                upc = "",
                diamondCode = "",
                ean = "",
                issn = "",
                format = "",
                pageCount = 32,
                textObjects = emptyList(),
                resourceUri = "",
                urls = emptyList(),
                series = SeriesSummary("", ""),
                variants = emptyList(),
                collections = emptyList(),
                collectedIssues = emptyList(),
                dates = emptyList(),
                prices = emptyList(),
                thumbnail = Image("", ""),
                images = emptyList(),
                creators = CreatorList(0, 0, "", emptyList()),
                characters = CharacterList(0, 0, "", emptyList()),
                stories = StoryList(0, 0, "", emptyList()),
                events = EventList(0, 0, "", emptyList())
            )
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun CharacterComicsListPreview() {
        CharacterComicsList(
            comicList = listOf(
                Comic(
                    0,
                    0,
                    "Comic 1",
                    1,
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    20,
                    emptyList(),
                    "",
                    emptyList(),
                    SeriesSummary("", ""),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    Image("", ""),
                    emptyList(),
                    CreatorList(0, 0, "", emptyList()),
                    CharacterList(0, 0, "", emptyList()),
                    StoryList(0, 0, "", emptyList()),
                    EventList(0, 0, "", emptyList())
                ),
                Comic(
                    1,
                    1,
                    "Comic 2",
                    2,
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    32,
                    emptyList(),
                    "",
                    emptyList(),
                    SeriesSummary("", ""),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    Image("", ""),
                    emptyList(),
                    CreatorList(0, 0, "", emptyList()),
                    CharacterList(0, 0, "", emptyList()),
                    StoryList(0, 0, "", emptyList()),
                    EventList(0, 0, "", emptyList())
                ),
                Comic(
                    2,
                    2,
                    "Comic 3",
                    3,
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    24,
                    emptyList(),
                    "",
                    emptyList(),
                    SeriesSummary("", ""),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    Image("", ""),
                    emptyList(),
                    CreatorList(0, 0, "", emptyList()),
                    CharacterList(0, 0, "", emptyList()),
                    StoryList(0, 0, "", emptyList()),
                    EventList(0, 0, "", emptyList())
                )
            )
        )
    }

}

