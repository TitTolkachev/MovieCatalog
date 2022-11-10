package com.example.moviecatalog.network.review

import com.example.moviecatalog.network.dataclasses.models.ReviewModifyModel
import retrofit2.http.*

interface ReviewApi {

    @POST("api/movie/{movieId}/review/add")
    suspend fun addReview(@Path("movieId") movieId: String, @Body body: ReviewModifyModel)

    @PUT("api/movie/{movieId}/review/{id}/edit")
    suspend fun editReview(@Path("movieId") movieId: String, @Path("id") id: String, @Body body: ReviewModifyModel)

    @DELETE("api/movie/{movieId}/review/{id}/delete")
    suspend fun deleteReview(@Path("movieId") movieId: String, @Path("id") id: String)
}