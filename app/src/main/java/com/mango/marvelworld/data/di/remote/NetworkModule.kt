package com.mango.marvelworld.data.di.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.data.source.remote.RemoteCharactersListDataSource
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    // Dependencies to fetch data for CharactersList screen
    @Provides
    fun provideCharactersListDataStore(marvelApi: MarvelApi): CharactersListDataSource {
        return RemoteCharactersListDataSource(marvelApi)
    }

}