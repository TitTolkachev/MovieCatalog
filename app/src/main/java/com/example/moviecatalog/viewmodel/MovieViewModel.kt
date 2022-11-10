package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.dataclasses.models.MovieDetailsModel
import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesRepository
import com.example.moviecatalog.network.movie.MovieRepository
import com.example.moviecatalog.network.review.ReviewRepository
import com.example.moviecatalog.network.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val movieRepository = MovieRepository()
private val userRepository = UserRepository()
private val favoriteMoviesRepository = FavoriteMoviesRepository()
private val reviewRepository = ReviewRepository()

class MovieViewModel(private val navController: NavController) : ViewModel() {

    var myReviewExists = mutableStateOf(false)

    fun getMovieDetails(
        coroutineScope: CoroutineScope,
        context: Context,
        movieId: String,
        details: MutableState<MovieDetailsModel>,
        user: MutableState<ProfileModel>
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            movieRepository.getMoviesDetails(movieId)
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            details.value = it
                            details.value.reviews?.forEach {
                                if (it.author.userId == user.value.id)
                                    myReviewExists.value = true
                            }
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Не удалось загрузить фильм",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    fun getUser(
        coroutineScope: CoroutineScope,
        user: MutableState<ProfileModel>,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            userRepository.getProfile()
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            user.value = it
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Не удалось получить данные о пользователе",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    fun addMovieToFavourites(
        movieId: String,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteMoviesRepository.addMovie(movieId)
        }
    }

    fun updateMovieScreen(movieId: String) {
        navController.popBackStack()
        navController.navigate(Screen.MovieScreen.route + "/${movieId}")
    }

    fun navigateToMainScreen() {
        navController.popBackStack()
    }

    fun deleteReview(
        coroutineScope: CoroutineScope,
        movieId: String,
        id: String
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            reviewRepository.deleteReview(movieId, id)
        }
    }
}