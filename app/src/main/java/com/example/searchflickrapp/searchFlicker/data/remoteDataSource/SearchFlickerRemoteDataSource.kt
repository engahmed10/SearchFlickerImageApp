package com.example.searchflickrapp.searchFlicker.data.remoteDataSource

import com.example.searchflickrapp.searchFlicker.data.api.SearchFlickerApiService
import javax.inject.Inject

class SearchFlickerRemoteDataSource @Inject constructor(
    private val apiService: SearchFlickerApiService
) {

    suspend fun getSearchResultFromApi(query: String) = apiService.getSearchResult(query)

}
