package com.mango.marvelworld.data.remote

import com.mango.marvelworld.data.remote.characterdetail.ComicDataWrapperDto
import com.mango.marvelworld.data.remote.characterlist.RootDto
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
    ): RootDto

    @GET("public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): RootDto

    @GET("public/characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Long,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): ComicDataWrapperDto
}