package com.example.moviecatalog.navigation.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.moviecatalog.navigation.HOME_GRAPH_ROUTE
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.view.MainScreen
import com.example.moviecatalog.view.MovieScreen
import com.example.moviecatalog.view.ProfileScreen
import kotlinx.coroutines.DelicateCoroutinesApi

@ExperimentalFoundationApi
@DelicateCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Launch.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(navController)
        }
        composable(
            route = Screen.Profile.route,
        ) {
            ProfileScreen(navController)
        }
        composable(
            route = Screen.MovieScreen.route+"/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) {
            val movieId = it.arguments?.getInt("movieId")!!
            MovieScreen(navController, movieId)
        }
    }
}