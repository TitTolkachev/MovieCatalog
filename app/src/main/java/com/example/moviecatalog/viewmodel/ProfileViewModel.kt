package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.datastore.StoreAccessToken
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.network.Network
import com.example.moviecatalog.network.auth.AuthRepository
import com.example.moviecatalog.network.dataclasses.models.ProfileModel
import com.example.moviecatalog.network.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val userRepository = UserRepository()
private val authRepository = AuthRepository()

class ProfileViewModel(private val navController: NavController) : ViewModel() {

    var user = mutableStateOf(
        ProfileModel(
            "",
            "",
            "",
            "",
            "",
            "",
            0
        )
    )

    fun getProfileDetails(
        coroutineScope: CoroutineScope,
        context: Context,
        email: MutableState<String>,
        avatarLink: MutableState<String>,
        name: MutableState<String>,
        datePicked: MutableState<String>,
        birthDate: MutableState<String>,
        isMaleChosen: MutableState<Boolean>,
        isFemaleChosen: MutableState<Boolean>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            userRepository.getProfile()
                .collect { result ->
                    result.onSuccess {
                        launch(Dispatchers.Main) {
                            user.value = it
                            email.value = it.email
                            avatarLink.value = it.avatarLink
                            name.value = it.name
                            datePicked.value = "${
                                it.birthDate.subSequence(8, 10)
                            }.${
                                it.birthDate.subSequence(5, 7)
                            }.${it.birthDate.subSequence(0, 4)}"
                            birthDate.value = it.birthDate
                            isMaleChosen.value = it.gender == 0
                            isFemaleChosen.value = it.gender == 1
                        }
                    }.onFailure {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_profile_data_not_found),
                                Toast.LENGTH_SHORT
                            ).show()

                            coroutineScope.launch(Dispatchers.IO) {
                                authRepository.logout()
                                StoreAccessToken(context).saveAccessToken("")
                            }
                            Network.token = ""
                            navigateToSignInScreen()
                        }
                    }
                }
        }
    }

    fun logout(coroutineScope: CoroutineScope, context: Context) {
        coroutineScope.launch(Dispatchers.IO) {
            authRepository.logout()
            StoreAccessToken(context).saveAccessToken("")
        }
        Network.token = ""
        navigateToSignInScreen()
    }

    fun navigateToMainScreen() {
        navController.popBackStack()
    }

    private fun navigateToSignInScreen() {
        navController.popBackStack()
        navController.popBackStack()
        navController.navigate(Screen.SignIn.route)
    }

    fun saveProfileChanges(coroutineScope: CoroutineScope, body: ProfileModel) {

        coroutineScope.launch(Dispatchers.IO) {
            userRepository.putProfile(body)
        }

        navController.popBackStack()
        navController.navigate(Screen.Profile.route)
    }
}