package com.mango.marvelworld.data.source.interfaces

import com.mango.marvelworld.data.remote.characterdetail.ComicDataContainerDto
import com.mango.marvelworld.data.remote.characterlist.CharacterDataContainerDto

interface CharactersListDataSource {
    suspend fun getCharacters(offset: Long): CharacterDataContainerDto
    suspend fun getCharacter(characterId: Long): CharacterDataContainerDto
    suspend fun getCharacterComics(characterId: Long) : ComicDataContainerDto
}