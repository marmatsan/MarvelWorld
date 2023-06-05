package com.mango.marvelworld.domain.repository.list

import com.mango.marvelworld.domain.models.CharacterDataContainer

interface CharactersListDataRepository {
    suspend fun getCharacters(offset : Long) : CharacterDataContainer
    suspend fun getCharacter(characterId : Long) : CharacterDataContainer
}