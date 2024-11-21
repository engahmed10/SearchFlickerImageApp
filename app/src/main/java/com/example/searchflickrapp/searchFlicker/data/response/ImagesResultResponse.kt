package com.example.searchflickrapp.searchFlicker.data.response

data class ImagesResultResponse(
    val description: String?,
    val generator: String?,
    val items: List<Item>?,
    val link: String?,
    val modified: String?,
    val title: String?
)