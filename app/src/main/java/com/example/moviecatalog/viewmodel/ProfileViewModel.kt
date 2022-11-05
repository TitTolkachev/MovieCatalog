package com.example.moviecatalog.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
                            birthDate.value = "${
                                it.birthDate.subSequence(8, 10)
                            }.${
                                it.birthDate.subSequence(5, 7)
                            }.${it.birthDate.subSequence(0, 4)}"
                            isMaleChosen.value = it.gender == 0
                            isFemaleChosen.value = it.gender == 1
                        }
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