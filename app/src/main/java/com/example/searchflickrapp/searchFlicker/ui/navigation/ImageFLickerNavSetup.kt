package com.example.searchflickrapp.searchFlicker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchflickrapp.searchFlicker.data.response.Item
import com.example.searchflickrapp.searchFlicker.ui.screen.ImageDetailsScreen

import com.example.searchflickrapp.searchFlicker.ui.screen.ImageScreen
import com.example.searchflickrapp.searchFlicker.ui.screen.imageRoute
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun ImageFlickderNavSetup(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = ImageFlickerNavigationRoute.ImagesScreen.route
    ) {
        composable(ImageFlickerNavigationRoute.ImagesScreen.route) {
            ImageScreen(navController, modifier)
        }
        composable(imageRoute) { backStack ->
            val imageJson = backStack.arguments?.getString("image")
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter(Item::class.java)
            val imageObject = imageJson?.let { jsonAdapter.fromJson(it) }
            ImageDetailsScreen(navController, modifier, imageObject)
        }
    }
}