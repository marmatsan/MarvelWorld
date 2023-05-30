package com.mango.marvelworld.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apiKey") apiPublicKey : String,
        @Query("hash") hash : String,
        @Query("offset") offset : String
    ): CharacterDataContainerDto
}