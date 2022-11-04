package com.example.moviecatalog.network.dataclasses.models

@kotlinx.serialization.Serializable
data class ProfileModel(
    val id: String,
    val nickName: String? = null,
    val email: String,
    val avatarLink: String? = null,
    val name: String,
    val birthDate: String,
    val gender: Int
)
