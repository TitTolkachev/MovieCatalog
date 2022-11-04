package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import com.example.moviecatalog.network.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val userRepository = UserRepository()

class ProfileViewModel(private val navController: NavController) : ViewModel() {

    lateinit var user: ProfileModel

    fun getProfileDetails(
        coroutineScope: CoroutineScope,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            userRepository.getProfile()
                .collect { result ->
                    result.onSuccess {
                        user = it
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Не удалось получить данные о пользователе",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    fun navigateToMainScreen() {
        navController.popBackStack()
    }

    fun navigateToSignInScreen() {
        navController.popBackStack()
        navController.popBackStack()
        navController.navigate(Screen.SignIn.route)
    }

    fun saveProfileChanges() {
        navController.popBackStack()
        navController.navigate(Screen.Profile.route)
    }
}