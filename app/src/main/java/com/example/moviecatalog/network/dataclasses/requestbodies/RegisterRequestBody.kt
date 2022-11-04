package com.example.moviecatalog.network.dataclasses.requestbodies

@kotlinx.serialization.Serializable
data class RegisterRequestBody(
    val userName: String,
    val name: String,
    val password: String,
    val email: String,
    val birthDate: String,
    val gender: Int
)