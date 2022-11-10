package com.example.moviecatalog.network.user

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository {

    private val api: UserApi = Network.getUserApi()

    suspend fun getProfile() = flow {
        try {
            emit(Result.success(api.getProfile()))
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun putProfile(body: ProfileModel) {
        try {
            api.putProfile(body)
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
        }
    }
}