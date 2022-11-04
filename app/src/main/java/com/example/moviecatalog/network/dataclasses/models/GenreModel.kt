package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class GenreModel(
    val id: String,
    val name: String?
)
