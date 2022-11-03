package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.navigation.Screen

class LaunchViewModel(private val navController: NavController) : ViewModel() {

    fun navigateToSignInScreen() {
        navController.popBackStack()
        navController.navigate(Screen.SignIn.route)
    }

    fun navigateToSignUpScreen() {
        navController.popBackStack()
        navController.navigate(Screen.SignUp.route)
    }

    fun navigateToMainScreen() {
        navController.popBackStack()
        navController.navigate(Screen.Main.route)
    }

    fun navigateToMovieScreen() {
        navController.popBackStack()
        navController.navigate(Screen.MovieScreen.route + "/${R.drawable.featured}")
    }
}