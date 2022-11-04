package com.example.moviecatalog.view.screens

import android.content.res.Resources
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.moviecatalog.R
import com.example.moviecatalog.network.dataclasses.requestbodies.LoginRequestBody
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.view.sharedsamples.NewOutlinedButton
import com.example.moviecatalog.view.sharedsamples.NewOutlinedTextField
import com.example.moviecatalog.viewmodel.SignInViewModel

@ExperimentalMaterial3Api
@Composable
fun SignInScreen(signInViewModel: SignInViewModel) {

    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val isValidInput = login.value != "" && password.value != ""

    val image = painterResource(id = R.drawable.group_57)

    val scale = remember {
        Animatable(0f)
    }
    val alpha = remember {
        Animatable(0f)
    }
    EntryAnimations(scale, alpha)

    val rememberScope = rememberCoroutineScope()
    val context = LocalContext.current

    Image(
        painter = image,
        contentDescription = LocalContext.current.getString(R.string.logo_content_description),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 106.dp - scale.value * 51.dp)
            .padding(top = 56.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = calculateTopPadding(image))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alpha.value)
        ) {
            NewOutlinedTextField(login, LocalContext.current.getString(R.string.sign_in_login_text), false)
            NewOutlinedTextField(password, LocalContext.current.getString(R.string.sign_in_password_text), true)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 14.dp)
        ) {
            NewOutlinedButton(
                isValidInput,
                LocalContext.current.getString(R.string.sign_in_sign_in_btn_text)
            ) {
                signInViewModel.signIn(rememberScope, LoginRequestBody(login.value, password.value), context)
            }
            Button(
                onClick = {
                    if (scale.value > 0.7f)
                        signInViewModel.navigateToSignUpScreen()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.sign_in_sign_up_btn_text),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                )
            }
        }
    }
}


private fun calculateTopPadding(image: Painter): Dp {

    val screenWidth = Resources.getSystem().displayMetrics.xdpi.dp

    val widthImage = image.intrinsicSize.width.dp
    val heightImage = image.intrinsicSize.height.dp

    val neededWidth = screenWidth - (110.dp * (screenWidth / 360.dp))
    val neededHeight = (heightImage.value * neededWidth.value) / widthImage.value

    val extraPadding = 56.dp + 48.dp

    return neededHeight.dp + extraPadding
}


@Composable
private fun EntryAnimations(
    scale: Animatable<Float, AnimationVector1D>,
    alpha: Animatable<Float, AnimationVector1D>
) {
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = LinearOutSlowInEasing
            )
        )
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutLinearInEasing
            )
        )
    }
}