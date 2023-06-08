package com.mango.marvelworld.domain.utils

object Constants {
    object Room {
        const val DATABASE_NAME = "marvel_characters_db"
    }

    object Literals {
        const val error = "Error: "
        const val noRecentSearches = "No hay ninguna búsqueda reciente"
        const val dot = "."
    }

    object Properties {
        const val characterId = "characterId"
        const val portraitResolution = "/portrait_xlarge"
        const val comicDateType = "onsaleDate"
        const val remoteDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
        const val localDateTimeFormat = "dd/MM/yyyy"
    }

    // Companion properties
    val String.Companion.Empty
        inline get() = ""
}