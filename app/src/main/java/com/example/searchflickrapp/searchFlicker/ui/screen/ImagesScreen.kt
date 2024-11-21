package com.example.searchflickrapp.searchFlicker.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchflickrapp.searchFlicker.ui.SearchFlickerViewModel
import com.example.searchflickrapp.searchFlicker.ui.StateResult
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.searchflickrapp.searchFlicker.data.response.Item
import com.example.searchflickrapp.searchFlicker.ui.navigation.ImageFlickerNavigationRoute
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLEncoder

val imageRoute = ImageFlickerNavigationRoute.ImageDetailsScreen.route + "/?image={image}"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SearchFlickerViewModel = hiltViewModel()
) {

    val imageResult = viewModel.imagesState.collectAsState()
    val searchClicked = viewModel.searchClicked.collectAsState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.searchImage.value,
            onValueChange = { viewModel.searchImage.value = it },
            label = { Text("Search for images") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.searchImages(viewModel.searchImage.value)
                viewModel.setSearchClicked(true)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enter The City Name, please")
        }
        if (searchClicked.value) {
            LaunchedEffect(Unit) {
                viewModel.searchImages(viewModel.searchImage.value)
            }
            ImageList(navController, modifier, imageResult.value)
        }
    }

}

@Composable
fun ImageList(navController: NavHostController, modifier: Modifier, result: StateResult) {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(Item::class.java).lenient()
    fun String.urlEncode(): String {
        return URLEncoder.encode(this, "utf-8")
    }

    when (val state = result) {
        is StateResult.Error -> {

        }

        StateResult.Loading -> {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 8.dp
            )
        }

        is StateResult.Success -> {
            LazyColumn {
                item(state.data) {
                    state.data?.forEachIndexed { index, it ->
                        val imageJson = jsonAdapter.toJson(state.data[index]).urlEncode()
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.LightGray
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            onClick = {
                                //when you click on weather card it will go to details , i didn't  added new
                                // details but same card details was added with navigation process
                                //with new nav page
                                navController.navigate(
                                    imageRoute.replace(
                                        "{image}", imageJson
                                    )
                                )
                            }
                        ) {

                            Image(
                                painter =
                                rememberAsyncImagePainter(it.media?.m),
                                contentDescription = "Icon",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(2.dp))
                            )
                        }
                    }

                }
            }
        }

        else -> {}
    }

}