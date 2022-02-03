package com.ishanvohra.projectsuper.ui.fragments.details

import com.ishanvohra.projectsuper.network.RetrofitClient
import retrofit2.Retrofit

class CharacterDetailsRepository {

    private val baseUrl: String = "https://gateway.marvel.com/v1/public/"

    suspend fun getComicCollections(url: String, apiKey: String, timeStamp: String, hash: String) =
        RetrofitClient(baseUrl).instance.getComicCollections(url, apiKey, timeStamp, hash)

    suspend fun getSeriesCollections(url: String, apiKey: String, timeStamp: String, hash: String) =
        RetrofitClient(baseUrl).instance.getSeriesCollections(url, apiKey, timeStamp, hash)

}