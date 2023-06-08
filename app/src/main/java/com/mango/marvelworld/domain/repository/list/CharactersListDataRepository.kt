package com.mango.marvelworld.domain.repository.list

import com.mango.marvelworld.domain.models.characterlist.CharacterDataContainer

interface CharactersListDataRepository {
    suspend fun getCharacters(offset : Long) : CharacterDataContainer
}