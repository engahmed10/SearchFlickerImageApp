package com.example.searchflickrapp.searchFlicker.ui.navigation

sealed class ImageFlickerNavigationRoute(val route : String){

    object ImagesScreen : ImageFlickerNavigationRoute("images_screen")
    object ImageDetailsScreen : ImageFlickerNavigationRoute("image_details_screen")

}