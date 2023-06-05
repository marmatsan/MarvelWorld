package com.mango.marvelworld.ui.presentation.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import com.mango.marvelworld.ui.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val charactersListDataRepository: CharactersListDataRepository
) : ViewModel() {

    // Detail Screen: Character details
    private val _requestCharacterDetailsState: MutableStateFlow<RequestState<*>> by lazy {
        MutableStateFlow(RequestState.Idle)
    }

    val requestCharacterDetailsState: StateFlow<RequestState<*>>
        get() = _requestCharacterDetailsState

    fun fetchCharacterById(characterId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _requestCharacterDetailsState.update { RequestState.Loading }
            val characterDetail =
                charactersListDataRepository.getCharacter(characterId = characterId)
            _requestCharacterDetailsState.update { RequestState.Success(characterDetail) }
        }
    }

    // Detail Screen: Character comics
    private val _requestCharacterComicsState: MutableStateFlow<RequestState<*>> by lazy {
        MutableStateFlow(RequestState.Idle)
    }

    val requestCharacterComicsState: StateFlow<RequestState<*>>
        get() = _requestCharacterComicsState

    fun fetchCharacterComics(characterId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _requestCharacterComicsState.update { RequestState.Loading }
            val characterComics =
                charactersListDataRepository.getCharacterComics(characterId = characterId)
            _requestCharacterComicsState.update { RequestState.Success(characterComics) }
        }
    }

}