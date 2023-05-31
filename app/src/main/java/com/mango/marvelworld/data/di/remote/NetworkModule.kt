package com.mango.marvelworld.data.di.remote

import androidx.paging.ExperimentalPagingApi
import com.google.gson.GsonBuilder
import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.repository.list.CharactersListDataRepositoryImpl
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.data.source.remote.RemoteCharactersListDataSource
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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

    // Dependencies to fetch data for CharactersList screen
    @Provides
    fun provideCharactersListDataStore(marvelApi: MarvelApi): CharactersListDataSource {
        return RemoteCharactersListDataSource(marvelApi)
    }

    @Provides
    fun provideCharactersListRepository(dataStore: CharactersListDataSource): CharactersListDataRepository {
        return CharactersListDataRepositoryImpl(dataStore)
    }

}