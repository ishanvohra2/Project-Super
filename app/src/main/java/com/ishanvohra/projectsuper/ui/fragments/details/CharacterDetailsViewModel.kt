package com.ishanvohra.projectsuper.ui.fragments.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishanvohra.projectsuper.models.ComicCollection.ComicCollectionResponse
import com.ishanvohra.projectsuper.models.SeriesCollection.SeriesCollectionResponse
import com.ishanvohra.projectsuper.ui.fragments.home.HomeRepository
import kotlinx.coroutines.launch

class CharacterDetailsViewModel : ViewModel() {

    var comicResult = MutableLiveData<ComicCollectionResponse>()
    var seriesResult = MutableLiveData<SeriesCollectionResponse>()

    fun getComicCollections(url: String, apikey: String, timeStamp: String, hash: String) =
        viewModelScope.launch{
             loadComicCollections(url, apikey, timeStamp, hash)
        }

    fun getSeriesCollections(url: String, apikey: String, timeStamp: String, hash: String) =
        viewModelScope.launch{
            loadSeriesCollections(url, apikey, timeStamp, hash)
        }

    private suspend fun loadComicCollections(url: String, apikey: String, timeStamp: String, hash: String) {
        try{
            val response = CharacterDetailsRepository().getComicCollections(url, apikey, timeStamp, hash)

            if(response.isSuccessful)
                comicResult.postValue(response.body())
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    private suspend fun loadSeriesCollections(url: String, apikey: String, timeStamp: String, hash: String) {
        try{
            val response = CharacterDetailsRepository().getSeriesCollections(url, apikey, timeStamp, hash)

            if(response.isSuccessful)
                seriesResult.postValue(response.body())
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

}