package com.example.moviecatalog.network

import com.example.moviecatalog.network.auth.AuthApi
import com.example.moviecatalog.network.favoritemovies.FavoriteMoviesApi
import com.example.moviecatalog.network.movie.MovieApi
import com.example.moviecatalog.network.user.UserApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {

    private const val BASE_URL = "https://react-midterm.kreosoft.space/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
            addInterceptor(MyInterceptor())
            //authenticator(Authenticator.JAVA_NET_AUTHENTICATOR)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }

        return client.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(getHttpClient())
            .build()
    }

    private val retrofit: Retrofit = getRetrofit()

    var token: String = ""

    fun getAuthApi(): AuthApi = retrofit.create(AuthApi::class.java)
    fun getFavoriteMoviesApi(): FavoriteMoviesApi = retrofit.create(FavoriteMoviesApi::class.java)
    fun getMovieApi(): MovieApi = retrofit.create(MovieApi::class.java)
    fun getUserApi(): UserApi = retrofit.create(UserApi::class.java)
}