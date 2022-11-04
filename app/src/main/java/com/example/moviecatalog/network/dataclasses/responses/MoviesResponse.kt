package com.example.moviecatalog.network.dataclasses.responses

import com.example.moviecatalog.network.dataclasses.models.MovieElementModel
import com.example.moviecatalog.network.dataclasses.models.PageInfoModel

@kotlinx.serialization.Serializable
data class MoviesResponse(
    val pageInfo: PageInfoModel,
    val movies: MutableList<MovieElementModel> = mutableListOf()
)