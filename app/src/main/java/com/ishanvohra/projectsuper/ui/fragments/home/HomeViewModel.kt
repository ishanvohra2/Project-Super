package com.ishanvohra.projectsuper.ui.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishanvohra.projectsuper.models.SearchResult.SearchResultResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var searchResult = MutableLiveData<SearchResultResponse>()

    fun getSearchResult(name: String, apiKey: String, timeStamp: String, hash: String) = viewModelScope.launch {
        loadSearchResult(name, apiKey, timeStamp, hash)
    }


    private suspend fun loadSearchResult(name: String, apiKey: String, timeStamp: String, hash: String){
        try{
            val response = HomeRepository().getSearchResult(name, apiKey, timeStamp, hash)

            if(response.isSuccessful)
                searchResult.postValue(response.body())

        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

}