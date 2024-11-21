package com.example.searchflickrapp.searchFlicker.domin


import com.example.searchflickrapp.searchFlicker.data.repository.SearchFlickerRepositoryImpl
import com.example.searchflickrapp.searchFlicker.ui.StateResult
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
     val searchFlickerRepository: SearchFlickerRepositoryImpl
) {
    suspend operator fun invoke(query: String): StateResult {
        val result = searchFlickerRepository.getSearchResult(query)
        if (result.isSuccessful) {
            result.body()?.let {
                return StateResult.Success(it.items)
            }.also {
                return StateResult.Error(result.message())
            }
        } else {
            return StateResult.Error(" response not successful $result.message()")
        }
        return StateResult.Error(" response not successful $result.message()")

    }
}