package com.mango.marvelworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterDataContainerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract val dao : CharactersDao
}