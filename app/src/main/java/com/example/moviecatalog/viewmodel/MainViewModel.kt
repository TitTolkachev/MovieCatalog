package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.dataclasses.models.MovieElementModel
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesRepository
import com.example.moviecatalog.paging.MovieSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private val favoriteMoviesRepository = FavoriteMoviesRepository()

class MainViewModel(
    private val navController: NavController,
    scope: CoroutineScope
) : ViewModel() {

    fun getFavouriteMovies(
        favouriteMovies: SnapshotStateList<MovieElementModel>,
        coroutineScope: CoroutineScope,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteMoviesRepository.getMovies()
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            it.movies.forEach { item ->
                                favouriteMovies.add(item)
                            }
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

    val movie: Flow<PagingData<MovieElementModel>> = Pager(PagingConfig(pageSize = 6)) {
        MovieSource()
    }.flow.cachedIn(scope)

    fun navigateToProfileScreen() {
        navController.navigate(Screen.Profile.route)
    }

    fun navigateToMovie(movieElementModel: MovieElementModel) {
        navController.navigate(Screen.MovieScreen.route + "/${movieElementModel.id}")
    }

    fun removeMovieFromFavourites(
        favouriteMovies: SnapshotStateList<MovieElementModel>,
        movieElementModel: MovieElementModel,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteMoviesRepository.deleteMovie(movieElementModel.id)
            launch(Dispatchers.Main) {
                favouriteMovies.remove(movieElementModel)
            }
        }
    }
}