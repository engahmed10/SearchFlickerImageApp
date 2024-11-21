package com.example.searchflickrapp.searchFlicker.data.repository

import com.example.searchflickrapp.searchFlicker.data.response.ImagesResultResponse
import retrofit2.Response

interface SearchFlickerRepository {

    suspend fun getSearchResult(query: String): Response<ImagesResultResponse>
}