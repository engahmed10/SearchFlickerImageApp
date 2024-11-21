package com.example.searchflickrapp.searchFlicker.ui.screen

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.searchflickrapp.searchFlicker.data.response.Item


@SuppressLint("SetTextI18n")
@Composable
fun ImageDetailsScreen(navController: NavHostController, modifier: Modifier, image: Item?) {
    Card {
        val imageUrl = image?.media?.m
        Column {
            if (imageUrl != null) {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = "Image of a flickr photo"
                )
            }
            Text(text = ("Title: " + image?.title) ?: "")

            val htmlContent = """${image?.description}"""
            AndroidView(
                factory = { context ->
                    TextView(context)
                },
                update = {
                    it.text = HtmlCompat.fromHtml(htmlContent, HtmlCompat.FROM_HTML_MODE_COMPACT)
                }
            )

            Text(text = ("Author :" + image?.author) ?: "")
            Text(text = ("Published Date : " + image?.published) ?: "")

        }


    }
}

