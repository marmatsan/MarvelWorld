package com.mango.marvelworld.data.source.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.remote.characterlist.CharacterDataContainerDto
import com.mango.marvelworld.data.remote.MarvelApi
import com.mango.marvelworld.data.remote.characterdetail.ComicDataContainerDto
import com.mango.marvelworld.data.source.interfaces.CharactersListDataSource
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
        
    }


    private fun computeMD5Hash(ts: Long): String {
        val md5Digest = MessageDigest.getInstance("MD5")
        val combinedString = "$ts${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_PUBLIC_KEY}"
        val bytes = md5Digest.digest(combinedString.toByteArray())

        val hexString = StringBuilder()
        for (byte in bytes) {
            val hex = String.format("%02x", byte.toInt() and 0xFF)
            hexString.append(hex)
        }

        return hexString.toString()
    }
}