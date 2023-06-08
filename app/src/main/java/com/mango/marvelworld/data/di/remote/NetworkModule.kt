package com.mango.marvelworld.data.di.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.repository.detail.CharacterDetailDataRepositoryImpl
import com.mango.marvelworld.data.repository.list.CharactersListDataRepositoryImpl
import com.mango.marvelworld.data.source.interfaces.CharacterDetailDataSource
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.data.source.remote.RemoteCharacterDetailDataSource
import com.mango.marvelworld.data.source.remote.RemoteCharactersListDataSource
import com.mango.marvelworld.domain.repository.detail.CharacterDetailDataRepository
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

    // Dependencies to fetch data for the list screen
    @Provides
    fun provideCharactersListDataSource(marvelApi: MarvelApi): CharactersListDataSource {
        return RemoteCharactersListDataSource(marvelApi)
    }

    @Provides
    fun provideCharactersListRepository(dataSource: CharactersListDataSource): CharactersListDataRepository {
        return CharactersListDataRepositoryImpl(dataSource)
    }

    // Dependencies to fetch data for the detail screen
    @Provides
    fun provideCharacterComicsDataSource(marvelApi: MarvelApi) : CharacterDetailDataSource {
        return RemoteCharacterDetailDataSource(marvelApi)
    }

    @Provides
    fun provideCharacterComicsRepository(dataSource : CharacterDetailDataSource) : CharacterDetailDataRepository {
        return CharacterDetailDataRepositoryImpl(dataSource)
    }

}