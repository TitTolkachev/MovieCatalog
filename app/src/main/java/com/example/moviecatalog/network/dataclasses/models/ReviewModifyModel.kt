package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class ReviewModifyModel(
    val reviewText:  String,
    val rating: Int,
    val isAnonymous: Boolean
)
