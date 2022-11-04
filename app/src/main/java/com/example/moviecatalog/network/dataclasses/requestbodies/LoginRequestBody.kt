package com.example.moviecatalog.network.dataclasses.requestbodies

@kotlinx.serialization.Serializable
data class LoginRequestBody(
    val username: String,
    val password: String
)
