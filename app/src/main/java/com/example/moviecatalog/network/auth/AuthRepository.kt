package com.example.moviecatalog.network.auth

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.requestbodies.LoginRequestBody
import com.example.moviecatalog.network.dataclasses.requestbodies.RegisterRequestBody
import com.example.moviecatalog.network.dataclasses.responses.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository {

    private val api: AuthApi = Network.getAuthApi()

    suspend fun register(body: RegisterRequestBody): Flow<Result<TokenResponse>> = flow {
        try {
            val tokenData = api.register(body)
            Network.token = tokenData.token
            emit(Result.success(tokenData))
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun login(body: LoginRequestBody): Flow<Result<TokenResponse>> = flow {
        try {
            val tokenData = api.login(body)
            Network.token = tokenData.token
            emit(Result.success(tokenData))
        } catch (e: Exception) {
            Log.e("123456789101112", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun logout() = flow {
        try {
            api.logout()
            emit(Result.success(true))
        } catch (e: Exception) {
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)
}