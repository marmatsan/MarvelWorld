package com.mango.marvelworld.ui.presentation.characterlist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mango.marvelworld.domain.models.CharacterDataContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    listViewModel: ListViewModel
) {
    val characterDataContainer = listViewModel.charactersPagingFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = true,
                onActiveChange = {}
            ) {

            }
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            CharactersContainer(
                characterDataContainer = characterDataContainer
            )
        }
    }
}

@Composable
fun CharactersContainer(
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
        modifier = Modifier.fillMaxSize()
    ) {
        if (characterDataContainer.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
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
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}