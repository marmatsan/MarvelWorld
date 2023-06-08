package com.mango.marvelworld.data.repository.list

import com.mango.marvelworld.data.datasource.features.characterdetail.remote.mapper.toComicDataContainer
import com.mango.marvelworld.data.datasource.features.characterlist.remote.mapper.toCharacterDataContainer
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
}