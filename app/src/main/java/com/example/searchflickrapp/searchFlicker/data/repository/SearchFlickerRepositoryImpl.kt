package com.example.searchflickrapp.searchFlicker.data.repository

import com.example.searchflickrapp.searchFlicker.data.remoteDataSource.SearchFlickerRemoteDataSource

import javax.inject.Inject

class SearchFlickerRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchFlickerRemoteDataSource,
) : SearchFlickerRepository {
    override suspend fun getSearchResult(query: String)=
         remoteDataSource.getSearchResultFromApi(query)
}