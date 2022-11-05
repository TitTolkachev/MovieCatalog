package com.example.moviecatalog.view.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.viewmodel.LaunchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LaunchScreen(navController: NavController) {

    val launchViewModel = remember {
        LaunchViewModel(navController)
    }

    val rememberScope = rememberCoroutineScope()
    val context = LocalContext.current
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        rememberScope.launch (Dispatchers.IO) {
            launchViewModel.tryAccessToken(context, rememberScope)
        }
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.group_57),
            contentDescription = LocalContext.current.getString(R.string.logo_content_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 75.dp)
                .scale(scale.value)
        )
    }
}