package com.example.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.dataclasses.ProfileData
import com.example.moviecatalog.navigation.Screen

class ProfileViewModel(private val navController: NavController) : ViewModel() {
    val details = ProfileData(
        "",
        "Leon",
        "",
        "",
        "",
        "",
        0
    )

    fun navigateToMainScreen(){
        navController.popBackStack()
    }

    fun navigateToSignInScreen(){
        navController.popBackStack()
        navController.popBackStack()
        navController.navigate(Screen.SignIn.route)
    }

    fun saveProfileChanges(){
        navController.popBackStack()
        navController.navigate(Screen.Profile.route)
    }
}