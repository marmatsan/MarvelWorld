package com.mango.marvelworld.domain.usecases

import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import javax.inject.Inject

class CharactersListUseCase @Inject constructor(
    private val charactersListRepository: CharactersListDataRepository
) : CharactersListDataRepository {
    override suspend fun getCharacters(offset: Long): CharacterDataContainer =
        charactersListRepository.getCharacters(offset = offset)
}