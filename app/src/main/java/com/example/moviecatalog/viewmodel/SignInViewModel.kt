package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.navigation.Screen

class SignInViewModel(private val navController: NavController) : ViewModel() {

    fun navigateToMainScreen() {
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.SignIn.route) {
                inclusive = true
            }
        }
    }

    fun navigateToSignUpScreen(){
        navController.navigate(Screen.SignUp.route)
    }
}