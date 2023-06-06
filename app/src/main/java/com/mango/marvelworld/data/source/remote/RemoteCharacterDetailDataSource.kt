package com.mango.marvelworld.data.source.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.datasource.features.characterdetail.model.ComicDataContainerDto
import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDataContainerDto
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.source.interfaces.CharacterDetailDataSource
import com.mango.marvelworld.data.source.remote.util.computeMD5Hash
import java.time.ZonedDateTime
import javax.inject.Inject

class RemoteCharacterDetailDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharacterDetailDataSource {
    override suspend fun getCharacter(characterId: Long): CharacterDataContainerDto {
        val ts = ZonedDateTime.now().toInstant().toEpochMilli()
        val apikey = BuildConfig.API_PUBLIC_KEY
        val hash = computeMD5Hash(ts = ts)

        return marvelApi
            .getCharacter(
                characterId = characterId,
                ts = ts,
                apikey = apikey,
                hash = hash
            ).data
    }

    override suspend fun getCharacterComics(characterId: Long): ComicDataContainerDto {
        val ts = ZonedDateTime.now().toInstant().toEpochMilli()
        val apikey = BuildConfig.API_PUBLIC_KEY
        val hash = computeMD5Hash(ts = ts)

        return marvelApi
            .getCharacterComics(
                characterId = characterId,
                ts = ts,
                apikey = apikey,
                hash = hash
            ).data
    }
}