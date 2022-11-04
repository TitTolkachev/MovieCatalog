package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class ReviewShortModel(
    val id: String,
    val rating: Int
)
