package com.mango.marvelworld.data.source.remote

import com.mango.marvelworld.BuildConfig
import com.mango.marvelworld.data.remote.CharacterDataContainerDto
import com.mango.marvelworld.data.remote.MarvelApi
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