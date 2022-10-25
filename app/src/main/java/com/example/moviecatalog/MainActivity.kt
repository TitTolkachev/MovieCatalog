package com.example.moviecatalog

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviecatalog.navigation.nav_graph.SetUpNavGraph
import com.example.moviecatalog.ui.theme.MovieCatalogTheme
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieCatalogTheme {
                Surface(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    navController = rememberNavController()
                    SetUpNavGraph(navController = navController)
                }
            }
        }
    }
}