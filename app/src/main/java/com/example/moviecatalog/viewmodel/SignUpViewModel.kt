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
import com.example.moviecatalog.network.dataclasses.requestbodies.RegisterRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val navController: NavController) : ViewModel() {

    private val repository = AuthRepository()

    private fun navigateToMainScreen() {
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.SignIn.route) {
                inclusive = true
            }
        }
    }

    fun navigateToSignInScreen() {
        navController.popBackStack(Screen.SignIn.route, false)
    }

    fun signUp(
        coroutineScope: CoroutineScope,
        registerRequestBody: RegisterRequestBody,
        context: Context
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            repository.register(
                registerRequestBody
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
                            Toast.makeText(context, context.getString(R.string.toast_invalid_input), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}