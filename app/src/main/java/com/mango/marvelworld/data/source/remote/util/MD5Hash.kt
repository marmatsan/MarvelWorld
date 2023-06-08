package com.mango.marvelworld.data.source.remote.util

import com.mango.marvelworld.BuildConfig
import java.security.MessageDigest

fun computeMD5Hash(ts: Long): String {
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