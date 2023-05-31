package com.mango.marvelworld.ui.presentation.characterlist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey

@Composable
fun ListScreen(
    listViewModel: ListViewModel
) {
    val characterDataContainer = listViewModel.charactersPagingFlow.collectAsLazyPagingItems()

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