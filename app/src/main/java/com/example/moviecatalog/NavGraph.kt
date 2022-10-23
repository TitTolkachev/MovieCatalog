package com.example.moviecatalog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviecatalog.view.LaunchScreen
import com.example.moviecatalog.view.SignInScreen
import com.example.moviecatalog.view.SignUpScreen

@RequiresApi(Build.VERSION_CODES.N)
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
            SignInScreen(navController)
        }
        composable(
            route = Screen.SignUp.route
        ){
            SignUpScreen(navController)
        }
    }
}