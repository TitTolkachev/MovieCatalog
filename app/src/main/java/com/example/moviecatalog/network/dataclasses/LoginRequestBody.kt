package com.example.moviecatalog.network.dataclasses

@kotlinx.serialization.Serializable
data class LoginRequestBody(
    val username: String,
    val password: String
)
