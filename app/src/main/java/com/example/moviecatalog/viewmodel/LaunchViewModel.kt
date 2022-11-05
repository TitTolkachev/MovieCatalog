package com.example.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.datastore.StoreAccessToken
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val userRepository = UserRepository()

class LaunchViewModel(private val navController: NavController) : ViewModel() {

    suspend fun tryAccessToken(context: Context, coroutineScope: CoroutineScope) {

        val dataStore = StoreAccessToken(context)

        coroutineScope.launch(Dispatchers.IO) {
            dataStore.getAccessToken.collect { value ->
                Network.token = value.toString()
            }
        }

        coroutineScope.launch(Dispatchers.IO) {
            userRepository.getProfile()
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            delay(800L)
                            navigateToMainScreen()
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            delay(800L)
                            navigateToSignInScreen()
                        }
                    }
                }
        }
    }

    private fun navigateToSignInScreen() {
        navController.popBackStack()
        navController.navigate(Screen.SignIn.route)
    }

    private fun navigateToSignUpScreen() {
        navController.popBackStack()
        navController.navigate(Screen.SignUp.route)
    }

    private fun navigateToMainScreen() {
        navController.popBackStack()
        navController.navigate(Screen.Main.route)
    }

    private fun navigateToMovieScreen() {
        navController.popBackStack()
        navController.navigate(Screen.MovieScreen.route + "/${R.drawable.featured}")
    }
}