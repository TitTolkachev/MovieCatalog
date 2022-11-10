package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalog.network.dataclasses.models.ReviewModifyModel
import com.example.moviecatalog.network.review.ReviewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val reviewRepository = ReviewRepository()

class ReviewViewModel : ViewModel() {

    fun addReview(
        coroutineScope: CoroutineScope,
        movieId: String,
        body: ReviewModifyModel
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            reviewRepository.addReview(movieId, body)
        }
    }

    fun editReview(
        coroutineScope: CoroutineScope,
        movieId: String,
        id: String,
        body: ReviewModifyModel
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            reviewRepository.editReview(movieId, id, body)
        }
    }
}