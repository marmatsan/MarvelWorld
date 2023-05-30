package com.mango.marvelworld.data.local

import androidx.paging.PagingSource
import androidx.room.Query
import androidx.room.Upsert
import com.mango.marvelworld.domain.Constants

interface CharactersDao {
    @Upsert
    suspend fun upsertAll(characters: CharacterDataContainerEntity)

    @Query("SELECT * FROM ${Constants.Room.DATABASE_NAME}")
    fun pagingSource() : PagingSource<Int, CharacterDataContainerEntity>

    @Query("DELETE FROM ${Constants.Room.DATABASE_NAME}")
    suspend fun clearAll()
}