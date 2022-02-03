package com.ishanvohra.projectsuper.network

import com.ishanvohra.projectsuper.models.SearchResult.SearchResultResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("characters")
    suspend fun getSearchResult(@Query("nameStartsWith")name: String,
                                @Query("apikey")apiKey: String,
                                @Query("ts") timeStamp: String,
                                @Query("hash")hash: String
    ) : Response<SearchResultResponse>

}