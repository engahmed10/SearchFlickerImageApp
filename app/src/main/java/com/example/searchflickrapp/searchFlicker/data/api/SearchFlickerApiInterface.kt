package com.example.searchflickrapp.searchFlicker.data.api

import com.example.searchflickrapp.searchFlicker.data.response.ImagesResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchFlickerApiService {

    //https://api.flickr.com/services/feeds/photos_public.gneporcupine?format=json&nojsoncallback=1&tags=

    @GET("photos_public.gne")
    suspend fun getSearchResult(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
    ): Response<ImagesResultResponse>


}