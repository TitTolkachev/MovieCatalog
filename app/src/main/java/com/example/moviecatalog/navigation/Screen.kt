package com.example.moviecatalog.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String){
    object Launch: Screen(route = "launch_screen")
    object SignIn: Screen(route = "sign_in_screen")
    object SignUp: Screen(route = "sign_up_screen")
    object Main: Screen(route = "main_screen")
    object Profile: Screen(route = "profile_screen")
}
