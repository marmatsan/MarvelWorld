package com.mango.marvelworld.data.repository.detail

import com.mango.marvelworld.data.datasource.features.characterdetail.remote.mapper.toComicDataContainer
import com.mango.marvelworld.data.datasource.features.characterlist.remote.mapper.toCharacterDataContainer
import com.mango.marvelworld.data.source.interfaces.CharacterDetailDataSource
import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer
import com.mango.marvelworld.domain.repository.detail.CharacterDetailDataRepository
import javax.inject.Inject

class CharacterDetailDataRepositoryImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailDataSource
) : CharacterDetailDataRepository {

    override suspend fun getCharacter(characterId: Long): CharacterDataContainer {
        return characterDetailRepository
            .getCharacter(characterId = characterId)
            .toCharacterDataContainer()
    }

    override suspend fun getCharacterComics(characterId: Long): ComicDataContainer {
        return characterDetailRepository
            .getCharacterComics(characterId = characterId)
            .toComicDataContainer()
    }
}