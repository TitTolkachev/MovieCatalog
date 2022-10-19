package com.example.moviecatalog

sealed class Screen(val route: String){
    object Launch: Screen(route = "launch_screen")
    object SignIn: Screen(route = "sign_in_screen")
}
