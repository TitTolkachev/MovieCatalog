package com.example.moviecatalog.network.dataclasses

import com.example.moviecatalog.network.dataclasses.models.MovieElementModel

@kotlinx.serialization.Serializable
data class FavoriteMoviesResponse(
    val movies: MutableList<MovieElementModel> = mutableListOf()
)
