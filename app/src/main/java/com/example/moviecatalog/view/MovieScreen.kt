package com.example.moviecatalog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun MovieScreen(navController: NavController, movieId: Int) {
    LazyColumn {
        item {
            Image(
                painter = painterResource(id = movieId),
                contentDescription = "Movie poster",
//                modifier = Modifier
//                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}