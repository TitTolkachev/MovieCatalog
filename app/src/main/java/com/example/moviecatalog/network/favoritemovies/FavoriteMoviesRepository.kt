package com.example.moviecatalog.network.favoritemovies

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.FavoriteMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoriteMoviesRepository {

    private val api: FavoriteMoviesApi = Network.getFavoriteMoviesApi()

    suspend fun getMovies(): Flow<Result<FavoriteMoviesResponse>> = flow {
        try {
            val moviesData = api.getMovies()
            Log.d("123456789", moviesData.toString())
            emit(Result.success(moviesData))
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)
}