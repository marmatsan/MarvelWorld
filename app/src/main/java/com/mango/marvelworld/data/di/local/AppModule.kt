package com.mango.marvelworld.data.di.local

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.mango.marvelworld.domain.models.characterlist.cache.CharacterDataContainerEntity
import com.mango.marvelworld.data.local.CharactersDatabase
import com.mango.marvelworld.data.remote.pagination.CharactersRemoteMediator
import com.mango.marvelworld.domain.utils.Constants
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
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
        charactersDataRepository: CharactersListDataRepository
    ): Pager<Int, CharacterDataContainerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharactersRemoteMediator(
                charactersDb = charactersDb,
                charactersDataRepository = charactersDataRepository
            ),
            pagingSourceFactory = {
                charactersDb.dao.pagingSource()
            }
        )
    }

}