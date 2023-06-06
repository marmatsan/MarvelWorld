package com.mango.marvelworld.ui.presentation.characterlist

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mango.marvelworld.domain.Constants.Empty
import com.mango.marvelworld.domain.models.characterlist.Character
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.ui.activities.DetailActivity
import com.mango.marvelworld.ui.presentation.characterlist.components.SearchAppBar

@Composable
fun ListScreen(
    listViewModel: ListViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current

    // SearchAppBar properties and operations
    var queryText by rememberSaveable { mutableStateOf(String.Empty) }

    val onQueryTextChange: (newQueryText: String) -> Unit = { newQueryText ->
        queryText = newQueryText
    }

    var searchBarActive by rememberSaveable { mutableStateOf(false) }

    val onSearchBarActive: (isActive: Boolean) -> Unit = { isActive ->
        searchBarActive = isActive
        listViewModel.getAllCharacterDataContainers()
    }

    // ViewModel properties
    val characterDataContainer = listViewModel.charactersPagingFlow.collectAsLazyPagingItems()
    val cachedDataContainersState by listViewModel.cachedDataContainersState.collectAsStateWithLifecycle()

    // Actions when clicked on a character
    val onCharacterCachedItemClick: (Character) -> Unit = { character ->
        onSearchBarActive(false)
        queryText = String.Empty
        val intent = Intent(localContext, DetailActivity::class.java).apply {
            putExtra("characterId", character.id)
        }
        localContext.startActivity(intent)
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
            characterDataContainer = characterDataContainer,
            onCharacterCachedItemClick = onCharacterCachedItemClick
        )
    }
}

@Composable
fun CharactersContainer(
    modifier: Modifier,
    characterDataContainer: LazyPagingItems<CharacterDataContainer>,
    onCharacterCachedItemClick: (Character) -> Unit
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

    if (characterDataContainer.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingIcon()
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = characterDataContainer.itemCount,
                key = characterDataContainer.itemKey(),
                contentType = characterDataContainer.itemContentType()
            ) { index ->
                val characters = characterDataContainer[index]?.results
                characters?.forEach { character ->
                    CharacterListItem(
                        character = character,
                        onCharacterCachedItemClick = onCharacterCachedItemClick
                    )
                }
            }
            item {
                if (characterDataContainer.loadState.append is LoadState.Loading) {
                    LoadingIcon()
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