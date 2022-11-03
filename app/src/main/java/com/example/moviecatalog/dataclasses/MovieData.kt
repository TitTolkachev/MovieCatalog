package com.example.moviecatalog.dataclasses

data class MovieData(
    val name: String,
    val year: Int,
    val country: String,
    val time: Int,
    val tagline: String,
    val description: String,
    val director: String,
    val budget: Int,
    val fees: Int,
    val ageLimit: Int,
)
