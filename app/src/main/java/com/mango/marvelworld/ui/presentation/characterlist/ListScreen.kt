package com.mango.marvelworld.ui.presentation.characterlist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.mappers.characterlist.toCharacterDataContainer
import com.mango.marvelworld.domain.Constants.Empty
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.ui.presentation.characterlist.components.SearchAppBar

@Composable
fun ListScreen(
    listViewModel: ListViewModel
) {
    val characterDataContainer = listViewModel.charactersPagingFlow.collectAsLazyPagingItems()
    val cachedDataContainersState by listViewModel.cachedDataContainersState.collectAsStateWithLifecycle()

    var queryText by rememberSaveable { mutableStateOf(String.Empty) }
    var searchBarActive by rememberSaveable { mutableStateOf(false) }

    val onQueryTextChange: (newQueryText: String) -> Unit = { newQueryText ->
        queryText = newQueryText
    }

    val onSearchBarActive: (isActive: Boolean) -> Unit = { isActive ->
        searchBarActive = isActive
        listViewModel.getAllCharacterDataContainers()
    }

    val onCharacterCachedItemClick: (String) -> Unit = { characterName ->
        queryText = characterName
        onSearchBarActive(false)
    }

    Scaffold(
        topBar = {
            SearchAppBar(
                queryText = queryText,
                onQueryTextChange = onQueryTextChange,
                active = searchBarActive,
                onSearchBarActive = onSearchBarActive,
                cachedDataContainersState = cachedDataContainersState,
                onCharacterCachedItemClick = onCharacterCachedItemClick
            )
        }
    ) { paddingValues ->
        CharactersContainer(
            modifier = Modifier.padding(paddingValues),
            queryText = queryText,
            cachedDataContainersState = cachedDataContainersState,
            characterDataContainer = characterDataContainer
        )
    }
}

@Composable
fun CharactersContainer(
    modifier: Modifier,
    queryText: String,
    cachedDataContainersState: List<CharacterDataContainerEntity>,
    characterDataContainer: LazyPagingItems<CharacterDataContainer>
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = characterDataContainer.loadState) {
        val refresh = characterDataContainer.loadState.refresh
        if (refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + refresh.error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (characterDataContainer.loadState.refresh is LoadState.Loading) {
            LoadingIcon()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (queryText.isEmpty()) {
                    items(
                        count = characterDataContainer.itemCount,
                        key = characterDataContainer.itemKey(),
                        contentType = characterDataContainer.itemContentType()
                    ) { index ->
                        val characters = characterDataContainer[index]?.results
                        characters?.forEach { character ->
                            CharacterListItem(
                                character = character
                            )
                        }
                    }
                    item {
                        if (characterDataContainer.loadState.append is LoadState.Loading) {
                            LoadingIcon()
                        }
                    }
                } else {
                    items(cachedDataContainersState) { characterDataContainerEntity ->
                        characterDataContainerEntity
                            .toCharacterDataContainer()
                            .results
                            .filter { character ->
                                character.name.contains(queryText)
                            }
                            .forEach { character ->
                                CharacterListItem(
                                    character = character
                                )
                            }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingIcon() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp)
    )
}