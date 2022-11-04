package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class ReviewModel(
    val id: String,
    val rating: Int,
    val reviewText: String?,
    val isAnonymous: Boolean,
    val createDateTime: String,
    val author: UserShortModel
)
