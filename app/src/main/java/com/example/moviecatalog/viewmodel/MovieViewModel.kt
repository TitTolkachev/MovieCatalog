package com.example.moviecatalog.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.dataclasses.MovieData
import com.example.moviecatalog.view.screens.MovieReview
import java.time.ZonedDateTime

class MovieViewModel(private val navController: NavController) : ViewModel() {

    val details = MovieData(
        "Побег из Шоушенка",
        1994,
        "США",
        142,
        "«Страх - это кандалы. Надежда - это свобода»",
        "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения",
        "Фрэнк Дарабонт",
        25000000,
        28418687,
        16
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val movieReviews = mutableListOf(
        MovieReview(
            R.drawable.featured,
            "Spike",
            false,
            6,
            "Im the best yeah",
            ZonedDateTime.now()
        ),
        MovieReview(
            R.drawable.profileusericon,
            "Leon",
            true,
            9,
            "Сразу скажу, что фильм мне понравился. Люблю Фримэна, уважаю Роббинса. Читаю Кинга. Но рецензия красненькая.",
            ZonedDateTime.now()
        )
    )

    val movieGenres = mutableListOf(
        "драма",
        "боевик",
        "фантастик",
        "мелодрама"
    )

    fun navigateToMainScreen() {
        navController.popBackStack()
    }
}