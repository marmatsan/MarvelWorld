package com.mango.marvelworld.data.source.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.datasource.features.characterlist.model.CharacterDataContainerDto
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.datasource.features.characterdetail.model.ComicDataContainerDto
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
import com.mango.marvelworld.data.source.remote.util.computeMD5Hash
import java.security.MessageDigest
import java.time.ZonedDateTime
import javax.inject.Inject

class RemoteCharactersListDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersListDataSource {
    override suspend fun getCharacters(offset: Long): CharacterDataContainerDto {

        val ts = ZonedDateTime.now().toInstant().toEpochMilli()
        val apikey = BuildConfig.API_PUBLIC_KEY
        val hash = computeMD5Hash(ts = ts)

        return marvelApi
            .getCharacters(
                ts = ts,
                apikey = apikey,
                hash = hash,
                offset = offset
            ).data
    }

}