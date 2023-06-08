package com.mango.marvelworld.domain.repository.detail

import com.mango.marvelworld.domain.models.characterdetail.ComicDataContainer
import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer

interface CharacterDetailDataRepository {
    suspend fun getCharacter(characterId : Long) : CharacterDataContainer
    suspend fun getCharacterComics(characterId: Long): ComicDataContainer
}