package com.example.searchflickrapp.searchFlicker.ui

import com.example.searchflickrapp.searchFlicker.data.response.Item

sealed  class StateResult {
    object Loading : StateResult()
    object Empty : StateResult()
    data class Success(val data: List<Item>?) : StateResult()
    data class Error(val error: String) : StateResult()
}
