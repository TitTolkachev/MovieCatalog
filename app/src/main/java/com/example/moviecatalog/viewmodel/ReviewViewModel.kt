package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel

class ReviewViewModel(private val movieViewModel: MovieViewModel) : ViewModel() {

    val reviewText = ""
    val rating = 0
    val isAnonymous = false
}