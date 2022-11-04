package com.example.moviecatalog.network.user

import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {

    @GET("api/account/profile")
    suspend fun getProfile(): ProfileModel

    @PUT("api/account/profile")
    suspend fun putProfile(@Body body: ProfileModel)
}