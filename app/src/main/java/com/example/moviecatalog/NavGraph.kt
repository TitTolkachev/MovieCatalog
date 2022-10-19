package com.example.moviecatalog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@ExperimentalMaterial3Api
@Composable
fun SetUpNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route
    ){
        composable(
            route = Screen.Launch.route
        ){
            LaunchScreen(navController)
        }
        composable(
            route = Screen.SignIn.route
        ){
            SignInScreen()
        }
    }
}