package com.mango.marvelworld.data.source.interfaces

import com.mango.marvelworld.data.remote.CharacterDataContainerDto

interface CharactersListDataSource {
    suspend fun getCharacters(offset: Long): CharacterDataContainerDto
    suspend fun getCharacter(characterId: Long): CharacterDataContainerDto
}