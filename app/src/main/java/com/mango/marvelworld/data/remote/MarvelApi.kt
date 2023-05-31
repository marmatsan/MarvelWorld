package com.mango.marvelworld.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apikey : String,
        @Query("hash") hash : String,
        @Query("offset") offset : Long
    ): RootDto
}