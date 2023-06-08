package com.mango.marvelworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mango.marvelworld.domain.models.characterlist.cache.CharacterDataContainerEntity

@Database(
    entities = [CharacterDataContainerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract val dao : CharactersDao
}