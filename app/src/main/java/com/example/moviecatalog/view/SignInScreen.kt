package com.example.moviecatalog.view

import android.content.res.Resources
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.Screen
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@ExperimentalMaterial3Api
@Composable
fun SignInScreen(navController: NavController) {

    //ВНИМАНИЕ!!!
    //Переменные ниже нужно будет вынести отдельно!!!
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


    Image(
        painter = image,
        contentDescription = "Logo",
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
            NewOutlinedTextField(login, "Логин", false)
            NewOutlinedTextField(password, "Пароль", true)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            NewOutlinedButton(
                isValidInput,
                "Войти"
            ) {
                navController.navigate(Screen.Main.route){
                    popUpTo(Screen.SignIn.route){
                        inclusive = true
                    }
                }
            }
            Button(
                onClick = {
                    if (scale.value > 0.7f)
                        navController.navigate(Screen.SignUp.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Регистрация"
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


@ExperimentalMaterial3Api
@Composable
fun NewOutlinedTextField(
    message: MutableState<String>,
    placeholderText: String,
    isPassword: Boolean
) {
    OutlinedTextField(
        value = message.value,
        onValueChange = { newText ->
            message.value = newText
        },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholderText,
                fontFamily = ibmPlexSansFamily,
                fontSize = 14.sp
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            placeholderColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(
            fontFamily = ibmPlexSansFamily,
            fontSize = 14.sp
        ),
        singleLine = true,
        visualTransformation =
        if (isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions =
        if (isPassword)
            KeyboardOptions(keyboardType = KeyboardType.Password)
        else
            KeyboardOptions.Default
    )
}


@Composable
fun NewOutlinedButton(isValidInput: Boolean, buttonText: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        enabled = isValidInput,
        border = BorderStroke(
            if (!isValidInput)
                1.dp
            else
                0.dp,
            if (!isValidInput)
                MaterialTheme.colorScheme.outline
            else
                MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Text(
            text = buttonText
        )
    }
}