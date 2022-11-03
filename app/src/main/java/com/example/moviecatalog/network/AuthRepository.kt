package com.example.moviecatalog.network

import android.util.Log
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
            Log.e("123456789", tokenData.token)
            emit(Result.success(tokenData))
        } catch (e: Exception){
            Log.e("12345678910", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}