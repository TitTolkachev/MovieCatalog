package com.example.moviecatalog.network.movie

import com.example.moviecatalog.network.dataclasses.models.MovieDetailsModel
import com.example.moviecatalog.network.dataclasses.responses.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("api/movies/{page}")
    suspend fun getMovies(@Path("page") page: Int): MoviesResponse

    @GET("api/movies/details/{id}")
    suspend fun getMoviesDetails(@Path("id") movieId: String): MovieDetailsModel
}