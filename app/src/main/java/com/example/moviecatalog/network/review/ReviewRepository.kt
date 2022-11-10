package com.example.moviecatalog.network.review

import android.util.Log
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.dataclasses.models.ReviewModifyModel

class ReviewRepository {

    private val api: ReviewApi = Network.getReviewApi()

    suspend fun addReview(movieId: String, body: ReviewModifyModel) {
        try {
            api.addReview(movieId, body)
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
        }
    }

    suspend fun editReview(movieId: String, id: String, body: ReviewModifyModel) {
        try {
            api.editReview(movieId, id, body)
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
        }
    }

    suspend fun deleteReview(movieId: String, id: String) {
        try {
            api.deleteReview(movieId, id)
        } catch (e: Exception) {
            Log.e("12345678910", e.message.toString())
        }
    }
}