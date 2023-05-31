package com.mango.marvelworld.domain

object Constants {
    object Room {
        const val DATABASE_NAME = "marvel_characters_db"
    }

    // Companion properties
    val String.Companion.Empty
        inline get() = ""
}