package com.mango.marvelworld.data.source.interfaces

import com.mango.marvelworld.data.datasource.features.characterdetail.model.ComicDataContainerDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDataContainerDto

interface CharacterDetailDataSource {
    suspend fun getCharacter(characterId: Long): CharacterDataContainerDto
    suspend fun getCharacterComics(characterId: Long) : ComicDataContainerDto
}