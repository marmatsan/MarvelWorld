package com.mango.marvelworld.ui.presentation.features.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.mango.marvelworld.domain.models.characterlist.cache.CharacterDataContainerEntity
import com.mango.marvelworld.data.local.CharactersDatabase
import com.mango.marvelworld.data.datasource.features.characterlist.remote.mapper.toCharacterDataContainer
import com.mango.marvelworld.domain.models.characterlist.Character
import com.mango.marvelworld.domain.models.characterlist.cache.mapper.toCharacterDataContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val charactersDb: CharactersDatabase,
    private val pager: Pager<Int, CharacterDataContainerEntity>
) : ViewModel() {

    val charactersPagingFlow = pager
        .flow
        .map {
            it.map { characterDataContainerEntity ->
                characterDataContainerEntity.toCharacterDataContainer()
            }
        }
        .cachedIn(viewModelScope)

    // SearchAppBar searches
    private val _recentSearchesState: MutableStateFlow<MutableList<Character>> by lazy {
        MutableStateFlow(mutableListOf())
    }

    val recentSearchesState: StateFlow<MutableList<Character>>
        get() = _recentSearchesState

    fun addSearchedCharacter(searchedCharacter: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            _recentSearchesState.update { recentSearches ->
                recentSearches.apply {
                    add(searchedCharacter)
                }
            }
        }
    }

    // Cached characters
    private val _cachedDataContainersState: MutableStateFlow<List<CharacterDataContainerEntity>> by lazy {
        MutableStateFlow(emptyList())
    }

    val cachedDataContainersState: StateFlow<List<CharacterDataContainerEntity>>
        get() = _cachedDataContainersState

    fun getAllCharacterDataContainers() {
        viewModelScope.launch(Dispatchers.IO) {
            _cachedDataContainersState.update { charactersDb.dao.getAllCharacterDataContainers() }
        }
    }
}