package com.example.moviecatalog.network.dataclasses.responses

import com.example.moviecatalog.network.dataclasses.models.MovieElementModel

@kotlinx.serialization.Serializable
data class FavoriteMoviesResponse(
    val movies: MutableList<MovieElementModel> = mutableListOf()
)
