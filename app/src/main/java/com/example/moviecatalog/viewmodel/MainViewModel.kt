package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.navigation.Screen

class MainViewModel(private val navController: NavController) : ViewModel() {
    val favouriteMovies = mutableListOf(
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured
    )

    val galleryMovies = listOf(
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured,
            R.drawable.featured
        )

    fun navigateToProfileScreen() {
        navController.navigate(Screen.Profile.route)
    }

    fun navigateToMovie(id: Int){
        navController.navigate(Screen.MovieScreen.route + "/$id")
    }

    fun removeMovieFromFavourites(id: Int){
        favouriteMovies.remove(id)
    }
}