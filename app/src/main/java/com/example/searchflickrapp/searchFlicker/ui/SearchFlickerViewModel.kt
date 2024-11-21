package com.example.searchflickrapp.searchFlicker.ui


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchflickrapp.searchFlicker.domin.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchFlickerViewModel @Inject constructor(private val getImagesUsecse: GetImagesUseCase) :
    ViewModel() {
    private val imagesMutableState: MutableStateFlow<StateResult> =
        MutableStateFlow(StateResult.Empty)
    val imagesState: StateFlow<StateResult> = imagesMutableState
    val searchImage = mutableStateOf("")
    val selectedImage = mutableStateOf("")

    private val _searchClicked = MutableStateFlow(false)
    val searchClicked = _searchClicked.asStateFlow()

    fun setSearchClicked(value: Boolean) {
        _searchClicked.value = value
    }

    fun searchImages(query: String) {
        viewModelScope.launch(IO) {

            searchImage.value = query
            selectedImage.value = ""
            if (query.isEmpty()) {
                imagesMutableState.value = StateResult.Empty
            } else {
                imagesMutableState.value = getImagesUsecse(query)
            }
        }
    }

}

            