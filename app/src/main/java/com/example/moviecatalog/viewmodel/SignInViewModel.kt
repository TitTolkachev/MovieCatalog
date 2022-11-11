package com.example.moviecatalog.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.datastore.StoreAccessToken
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.auth.AuthRepository
import com.example.moviecatalog.network.dataclasses.requestbodies.LoginRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val repository = AuthRepository()

class SignInViewModel(private val navController: NavController) : ViewModel() {

    fun signIn(
        coroutineScope: CoroutineScope,
        loginRequestBody: LoginRequestBody,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            repository.login(
                loginRequestBody
            )
                .collect { result ->
                    result.onSuccess {
                        Log.d("123456", it.token)
                        StoreAccessToken(context).saveAccessToken(it.token)
                        launch(Dispatchers.Main) {
                            navigateToMainScreen()
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_invalid_nickname_or_password),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    private fun navigateToMainScreen() {
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.SignIn.route) {
                inclusive = true
            }
        }
    }

    fun navigateToSignUpScreen() {
        navController.navigate(Screen.SignUp.route)
    }
}