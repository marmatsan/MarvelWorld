package com.mango.marvelworld.ui.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.mappers.toCharacterDataContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    pager: Pager<Int, CharacterDataContainerEntity>
) : ViewModel() {

    val charactersPagingFlow = pager
        .flow
        .map {
            it.map { characterDataContainerEntity ->
                characterDataContainerEntity.toCharacterDataContainer()
            }
        }
        .cachedIn(viewModelScope)

}