package com.mango.marvelworld.domain.repository.list

import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer

interface CharactersListDataRepository {
    suspend fun getCharacters(offset : Long) : CharacterDataContainer
    suspend fun getCharacter(characterId : Long) : CharacterDataContainer
    suspend fun getCharacterComics(characterId: Long): ComicDataContainer
}