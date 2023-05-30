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
    override suspend fun getCharacters(offset: Int): CharacterDataContainerDto {

        val ts = ZonedDateTime.now().toInstant().toEpochMilli().toString()
        val hash = computeMD5(
            ts = ts,
            privateKey = BuildConfig.API_PRIVATE_KEY,
            publicKey = BuildConfig.API_PUBLIC_KEY
        )
        return marvelApi
            .getCharacters(
                ts = ts,
                apiPublicKey = BuildConfig.API_PUBLIC_KEY,
                hash = hash,
                offset = offset.toString()
            )
    }

    private fun computeMD5(ts: String, privateKey: String, publicKey: String): String {
        val md5Digest = MessageDigest.getInstance("MD5")
        val combinedString = "$ts$privateKey$publicKey"
        val bytes = md5Digest.digest(combinedString.toByteArray())

        // Convert the byte array to a hexadecimal string representation
        val hexString = StringBuilder()
        for (byte in bytes) {
            val hex = String.format("%02x", byte.toInt() and 0xFF)
            hexString.append(hex)
        }

        return hexString.toString()
    }
}