package com.example.searchflickrapp/*
package com.example.searchflickrapp

import androidx.lifecycle.ViewModel
import com.example.searchflickrapp.searchFlicker.data.response.Item
import com.example.searchflickrapp.searchFlicker.data.response.Media
import com.example.searchflickrapp.searchFlicker.domin.GetImagesUseCase
import com.example.searchflickrapp.searchFlicker.ui.SearchFlickerViewModel
import com.example.searchflickrapp.searchFlicker.ui.StateResult
import kotlinx.coroutines.withContext
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchFlickerViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()


    @Mock
    private lateinit var imageUseCase: GetImagesUseCase

    @Mock
    private lateinit var viewModel: SearchFlickerViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getImageData_WhenSuccess_ShouldReturnImageData() = runTest(testDispatcher) {
        val image_Name = "Porcupine"
        val imageData = listOf(
            Item(
                author = "author",
                author_id = "author_id",
                date_taken = "date_taken",
                description = "description",
                link = "link",
                media = Media("media"),
                published = "published",
                tags = "tags",
                title = "title",
            )
        )

        // Mock the use case to return weather data
        whenever(imageUseCase(imageData.toString())).thenReturn(
            StateResult.Success(imageData)
        )


        viewModel.searchImages(image_Name)

        //  state is updated
        advanceUntilIdle()
        val state = viewModel.imagesState.first()
        assertEquals(StateResult.Success(imageData), state)
    }

    @Test
    fun getImageData_WhenIOException_ShouldReturnErrorState() = runTest(testDispatcher) {
        val exception = IOException("Connection error")

        // Mock the use case to throw IOException
        withContext(Dispatchers.IO) {
            throw exception
        }
        // Assert that the state is set to error
        val state = viewModel.imagesState.first()
        assert(state is StateResult.Error)
    }

}

*/
