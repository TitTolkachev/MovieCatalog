package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.network.dataclasses.models.MovieDetailsModel
import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesRepository
import com.example.moviecatalog.network.movie.MovieRepository
import com.example.moviecatalog.network.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val movieRepository = MovieRepository()
private val userRepository = UserRepository()
private val favoriteMoviesRepository = FavoriteMoviesRepository()

class MovieViewModel(private val navController: NavController) : ViewModel() {

    var user = mutableStateOf(
        ProfileModel(
            id = "1",
            nickName = "123",
            email = "123",
            avatarLink = "123",
            name = "123",
            birthDate = "123",
            gender = 0
        )
    )

    var details = mutableStateOf(
        MovieDetailsModel(
            id = "2",
            year = 2000,
            time = 120,
            ageLimit = 16
        )
    )

    fun getMovieDetails(
        coroutineScope: CoroutineScope,
        context: Context,
        movieId: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            movieRepository.getMoviesDetails(movieId)
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            details.value = it
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
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
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

    fun navigateToMainScreen() {
        navController.popBackStack()
    }
}