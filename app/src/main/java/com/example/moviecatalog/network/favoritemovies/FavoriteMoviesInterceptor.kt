package com.example.moviecatalog.network.favoritemovies

import android.util.Log
import com.example.moviecatalog.network.Network
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class FavoriteMoviesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request().newBuilder().apply {
            addHeader("accept", "application/json")
            addHeader("content-Type", "application/x-www-form-urlencoded")
            addHeader("Authorization", "Bearer ${Network.token}")
        }.build()

        var response: Response? = null

        return try {
            response = chain.proceed(request)
            Log.d("123456", response.message)
            response
        } catch (e: Exception) {
            response?.close()
            chain.proceed(request)
        }
    }
}