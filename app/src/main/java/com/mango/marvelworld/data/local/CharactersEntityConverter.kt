package com.mango.marvelworld.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharactersEntityConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCharacterDataContainerEntity(entity: CharacterDataContainerEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toCharacterDataContainerEntity(json: String): CharacterDataContainerEntity {
        val type = object : TypeToken<CharacterDataContainerEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromThumbnailEntity(entity: ThumbnailEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toThumbnailEntity(json: String): ThumbnailEntity {
        val type = object : TypeToken<ThumbnailEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromComicsEntity(entity: ComicsEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toComicsEntity(json: String): ComicsEntity {
        val type = object : TypeToken<ComicsEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromCharacterEntity(entity: CharacterEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toCharacterEntity(json: String): CharacterEntity {
        val type = object : TypeToken<CharacterEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromComicSummaryEntity(entity: ComicSummaryEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toComicSummaryEntity(json: String): ComicSummaryEntity {
        val type = object : TypeToken<ComicSummaryEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSeriesEntity(entity: SeriesEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toSeriesEntity(json: String): SeriesEntity {
        val type = object : TypeToken<SeriesEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSeriesSummaryEntity(entity: SeriesSummaryEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toSeriesSummaryEntity(json: String): SeriesSummaryEntity {
        val type = object : TypeToken<SeriesSummaryEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromStoriesEntity(entity: StoriesEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toStoriesEntity(json: String): StoriesEntity {
        val type = object : TypeToken<StoriesEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromStorySummaryEntity(entity: StorySummaryEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toStorySummaryEntity(json: String): StorySummaryEntity {
        val type = object : TypeToken<StorySummaryEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromEventsEntity(entity: EventsEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toEventsEntity(json: String): EventsEntity {
        val type = object : TypeToken<EventsEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromEventSummaryEntity(entity: EventSummaryEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toEventSummaryEntity(json: String): EventSummaryEntity {
        val type = object : TypeToken<EventSummaryEntity>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromUrlEntity(entity: UrlEntity): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun toUrlEntity(json: String): UrlEntity {
        val type = object : TypeToken<UrlEntity>() {}.type
        return gson.fromJson(json, type)
    }
}