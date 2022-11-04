package com.example.moviecatalog.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.dataclasses.models.MovieElementModel
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val favoriteMoviesRepository = FavoriteMoviesRepository()

class MainViewModel(private val navController: NavController) : ViewModel() {

    val favoriteMovies = mutableListOf<MovieElementModel>()

    fun getFavouriteMovies(
        coroutineScope: CoroutineScope,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteMoviesRepository.getMovies()
                .collect { result ->
                    result.onSuccess {
                        Log.d("123456", it.toString())
                        it.movies.forEach { item ->
                            favoriteMovies.add(item)
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

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

    fun navigateToMovie(movieElementModel: MovieElementModel) {
        navController.navigate(Screen.MovieScreen.route + "/${movieElementModel.id}")
    }

    fun removeMovieFromFavourites(movieElementModel: MovieElementModel) {
        favoriteMovies.remove(movieElementModel)
    }
}