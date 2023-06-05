package com.mango.marvelworld.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mango.marvelworld.data.local.CharacterDataContainerEntity
import com.mango.marvelworld.data.local.CharactersDatabase
import com.mango.marvelworld.data.mappers.toCharacterDataContainerEntity
import com.mango.marvelworld.domain.repository.list.CharactersListDataRepository
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val charactersDb: CharactersDatabase,
    private val charactersDataRepository: CharactersListDataRepository
) : RemoteMediator<Int, CharacterDataContainerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDataContainerEntity>
    ): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true) // Not supported
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        lastItem.offset + state.config.pageSize
                    }
                }
            }

            val characterDataContainer = charactersDataRepository.getCharacters(offset = loadKey)

            charactersDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    charactersDb.dao.clearAll()
                }
                val characterDataContainerEntity =
                    characterDataContainer.toCharacterDataContainerEntity()
                charactersDb.dao.upsertAll(characterDataContainerEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = characterDataContainer.offset == characterDataContainer.total
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}