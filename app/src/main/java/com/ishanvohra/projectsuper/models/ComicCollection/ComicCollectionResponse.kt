package com.ishanvohra.projectsuper.models.ComicCollection

import com.google.gson.annotations.SerializedName

data class ComicCollectionResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    @SerializedName("data")
    val `data`: Data,
    val etag: String,
    val status: String
) {

    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<Result>,
        val total: Int
    )

    data class Result(
        val characters: Characters,
        val collectedIssues: List<Any>,
        val collections: List<Collection>,
        val creators: Creators,
        val dates: List<Date>,
        val description: String,
        val diamondCode: String,
        val digitalId: Int,
        val ean: String,
        val events: Events,
        val format: String,
        val id: Int,
        val images: List<Image>,
        val isbn: String,
        val issn: String,
        val issueNumber: Int,
        val modified: String,
        val pageCount: Int,
        val prices: List<Price>,
        val resourceURI: String,
        val series: Series,
        val stories: Stories,
        val textObjects: List<TextObject>,
        val thumbnail: Thumbnail,
        val title: String,
        val upc: String,
        val urls: List<Url>,
        val variantDescription: String,
        val variants: List<Any>
    )

    data class Characters(
        val available: Int,
        val collectionURI: String,
        val items: List<Item>,
        val returned: Int
    )

    data class Collection(
        val name: String,
        val resourceURI: String
    )

    data class Creators(
        val available: Int,
        val collectionURI: String,
        val items: List<ItemX>,
        val returned: Int
    )

    data class Date(
        val date: String,
        val type: String
    )

    data class Events(
        val available: Int,
        val collectionURI: String,
        val items: List<Any>,
        val returned: Int
    )

    data class Image(
        val extension: String,
        val path: String
    )

    data class Price(
        val price: Int,
        val type: String
    )

    data class Series(
        val name: String,
        val resourceURI: String
    )

    data class Stories(
        val available: Int,
        val collectionURI: String,
        val items: List<ItemXX>,
        val returned: Int
    )

    data class TextObject(
        val language: String,
        val text: String,
        val type: String
    )

    data class Thumbnail(
        val extension: String,
        val path: String
    )

    data class Url(
        val type: String,
        val url: String
    )

    data class Item(
        val name: String,
        val resourceURI: String
    )

    data class ItemX(
        val name: String,
        val resourceURI: String,
        val role: String
    )

    data class ItemXX(
        val name: String,
        val resourceURI: String,
        val type: String
    )
}