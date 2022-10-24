package com.example.moviecatalog

sealed class Screen(val route: String){
    object Launch: Screen(route = "launch_screen")
    object SignIn: Screen(route = "sign_in_screen")
    object SignUp: Screen(route = "sign_up_screen")
    object Main: Screen(route = "main_screen")
}
