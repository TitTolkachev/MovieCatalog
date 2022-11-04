package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class MovieDetailsModel(
    val id: String,
    val name: String? = null,
    val poster: String? = null,
    val year: Int,
    val country: String? = null,
    val genres: MutableList<GenreModel>? = null,
    val reviews: MutableList<ReviewModel>? = null,
    val time: Int,
    val tagline: String? = null,
    val description: String? = null,
    val director: String? = null,
    val budget: Int? = null,
    val fees: Int? = null,
    val ageLimit: Int
)
