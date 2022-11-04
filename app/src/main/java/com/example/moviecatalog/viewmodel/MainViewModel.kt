package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.dataclasses.models.MovieElementModel
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesRepository
import com.example.moviecatalog.network.movie.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val favoriteMoviesRepository = FavoriteMoviesRepository()
private val movieRepository = MovieRepository()

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
                        it.movies.forEach { item ->
                            favoriteMovies.add(item)
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Избранные изображения не удалось загрузить",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    val galleryMovies: MutableList<MovieElementModel> = mutableListOf()

    fun getGalleryMovies(
        coroutineScope: CoroutineScope,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            movieRepository.getMovies()
                .collect { result ->
                    result.onSuccess {
                        it.movies.forEach { item ->
                            galleryMovies.add(item)
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Галерею не удалось загрузить",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

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