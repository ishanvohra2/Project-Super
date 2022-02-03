package com.ishanvohra.projectsuper.ui.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ishanvohra.projectsuper.models.SearchResult.SearchResultResponse
import com.ishanvohra.projectsuper.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private val baseUrl: String = "https://gateway.marvel.com/v1/public/"

    suspend fun getSearchResult(name: String, apiKey: String, timeStamp: String, hash: String) =
        RetrofitClient(baseUrl).instance.getSearchResult(name, apiKey, timeStamp, hash)

}