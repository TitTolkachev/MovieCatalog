package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class UserShortModel(
    val userId: String,
    val nickName: String?,
    val avatar: String?
)
