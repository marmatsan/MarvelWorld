package com.mango.marvelworld.data.remote

import com.mango.marvelworld.data.datasource.features.characterdetail.model.ComicDataWrapperDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDataWrapperDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Long
    ): CharacterDataWrapperDto

    @GET("public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharacterDataWrapperDto

    @GET("public/characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): ComicDataWrapperDto
}