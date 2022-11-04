package com.example.moviecatalog.network.auth

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.LoginRequestBody
import com.example.moviecatalog.network.dataclasses.RegisterRequestBody
import com.example.moviecatalog.network.dataclasses.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository {

    private val api: AuthApi = Network.getAuthApi()

    suspend fun register(body: RegisterRequestBody): Flow<Result<TokenResponse>> = flow {
        try {
            val tokenData = api.register(body)
            Network.token = tokenData
            Log.d("123456789", tokenData.token)
            emit(Result.success(tokenData))
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun login(body: LoginRequestBody): Flow<Result<TokenResponse>> = flow {
        try {
            val tokenData = api.login(body)
            Network.token = tokenData
            Log.d("1234567891011", tokenData.token)
            emit(Result.success(tokenData))
        } catch (e: Exception) {
            Log.e("123456789101112", e.message.toString())
            emit(Result.failure(Throwable(e)))
        }
    }.flowOn(Dispatchers.IO)
}