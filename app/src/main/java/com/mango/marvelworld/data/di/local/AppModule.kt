package com.mango.marvelworld.data.di.local

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.local.CharactersDatabase
import com.mango.marvelworld.domain.Constants
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object AppModule {

    @Provides
    @Singleton
    fun provideCharactersDatabase(@ApplicationContext context: Context): CharactersDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = CharactersDatabase::class.java,
            name = Constants.Room.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharactersPager(
        charactersDb: CharactersDatabase,
        marvelApi: CharactersListDataRepository
    ): Pager<Int, CharacterDataContainerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MoviesByGenreRemoteMediator(
                moviesByGenreDb = moviesByGenreDb,
                movieListDataRepository = moviesApi
            ),
            pagingSourceFactory = {
                moviesByGenreDb.dao.pagingSource()
            }
        )
    }

}