package com.example.moviecatalog.network.movie

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.models.MovieDetailsModel
import com.example.moviecatalog.network.dataclasses.responses.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository {

    private val api: MovieApi = Network.getMovieApi()

    suspend fun getMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
        try {
            emit(Result.success(api.getMovies(page)))
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getMoviesDetails(id: String): Flow<Result<MovieDetailsModel>> = flow {
        try {
            emit(Result.success(api.getMoviesDetails(id)))
        } catch (e: Exception) {
            Log.e("123456789101112", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)
}