package com.mango.marvelworld.data.repository.list

import com.mango.marvelworld.data.mappers.characterdetail.toComicDataContainer
import com.mango.marvelworld.data.mappers.characterlist.toCharacterDataContainer
import com.mango.marvelworld.data.remote.characterdetail.ComicDataContainerDto
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import javax.inject.Inject

class CharactersListDataRepositoryImpl @Inject constructor(
    private val charactersListRepository: CharactersListDataSource
) : CharactersListDataRepository {

    override suspend fun getCharacters(offset: Long): CharacterDataContainer {
        return charactersListRepository
            .getCharacters(offset = offset)
            .toCharacterDataContainer()
    }

    override suspend fun getCharacter(characterId: Long): CharacterDataContainer {
        return charactersListRepository
            .getCharacter(characterId = characterId)
            .toCharacterDataContainer()
    }

    override suspend fun getCharacterComics(characterId: Long): ComicDataContainer {
        return charactersListRepository
            .getCharacterComics(characterId = characterId)
            .toComicDataContainer()
    }

}