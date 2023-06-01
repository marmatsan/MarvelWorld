package com.mango.marvelworld.ui.presentation.characterlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mango.marvelworld.R
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.domain.Constants.Empty
import com.mango.marvelworld.domain.models.CharacterDataContainer
import com.mango.marvelworld.ui.presentation.characterlist.CharacterListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    queryText: String,
    onQueryTextChange: (String) -> Unit,
    active: Boolean,
    onSearchBarActive: (Boolean) -> Unit,
    cachedDataContainersState: List<CharacterDataContainerEntity>
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
                items(cachedDataContainersState) { characterDataContainer ->
                    characterDataContainer.results.forEach { character ->
                        Text(text = character.name)
                    }
                }
            }
        }
    }
}