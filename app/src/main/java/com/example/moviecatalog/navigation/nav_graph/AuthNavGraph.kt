package com.example.moviecatalog.navigation.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecatalog.navigation.AUTH_GRAPH_ROUTE
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.view.screens.SignInScreen
import com.example.moviecatalog.view.screens.SignUpScreen
import com.example.moviecatalog.viewmodel.SignInViewModel
import com.example.moviecatalog.viewmodel.SignUpViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Launch.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.SignIn.route,
        ){
            SignInScreen(SignInViewModel(navController))
        }
        composable(
            route = Screen.SignUp.route
        ){
            SignUpScreen(SignUpViewModel(navController))
        }
    }
}