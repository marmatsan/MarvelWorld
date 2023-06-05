package com.mango.marvelworld.data.repository.list

import com.mango.marvelworld.data.mappers.toCharacterDataContainer
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.domain.models.CharacterDataContainer
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import javax.inject.Inject

class CharactersListDataRepositoryImpl @Inject constructor(
    private val charactersListRepository: CharactersListDataSource
) : CharactersListDataRepository {

    override suspend fun getCharacters(offset: Long): CharacterDataContainer {
        val charactersDataContainerDto = charactersListRepository.getCharacters(offset = offset)
        return charactersDataContainerDto.toCharacterDataContainer()
    }

    override suspend fun getCharacter(characterId: Long): CharacterDataContainer {
        val charactersDataContainerDto =
            charactersListRepository.getCharacter(characterId = characterId)
        return charactersDataContainerDto.toCharacterDataContainer()
    }

}