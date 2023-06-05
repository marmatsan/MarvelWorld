package com.mango.marvelworld.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharactersEntityConverter {
    @TypeConverter
    fun fromCharacterEntityList(characterEntityList: List<CharacterEntity>): String {
        return Gson().toJson(characterEntityList)
    }

    @TypeConverter
    fun toCharacterEntityList(characterEntityListString: String): List<CharacterEntity> {
        val type = object : TypeToken<List<CharacterEntity>>() {}.type
        return Gson().fromJson(characterEntityListString, type)
    }
}