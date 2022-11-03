package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.AuthRepository

class SignUpViewModel(private val navController: NavController) : ViewModel() {

    val repository = AuthRepository()

    fun signUp() {
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.SignIn.route) {
                inclusive = true
            }
        }
    }

    fun navigateToSignInScreen() {
        navController.popBackStack(Screen.SignIn.route, false)
    }
}