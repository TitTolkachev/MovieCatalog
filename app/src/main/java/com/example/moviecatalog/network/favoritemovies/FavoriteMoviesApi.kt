package com.example.moviecatalog.network.favoritemovies

import com.example.moviecatalog.network.dataclasses.responses.FavoriteMoviesResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteMoviesApi {

    @GET("api/favorites")
    suspend fun getMovies(): FavoriteMoviesResponse

    @POST("api/favorites/{id}/add")
    suspend fun postMovie(@Path("id") id: String)

    @DELETE("api/favorites/{id}/delete")
    suspend fun deleteMovie(@Path("id") id: String)
}