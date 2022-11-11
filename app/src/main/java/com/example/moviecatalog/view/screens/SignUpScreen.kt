package com.example.moviecatalog.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.network.dataclasses.requestbodies.RegisterRequestBody
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.view.sharedsamples.NewDatePicker
import com.example.moviecatalog.view.sharedsamples.NewGenderCheckField
import com.example.moviecatalog.view.sharedsamples.NewOutlinedButton
import com.example.moviecatalog.view.sharedsamples.NewOutlinedTextField
import com.example.moviecatalog.viewmodel.SignUpViewModel
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition", "NewApi")
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController) {

    val signUpViewModel = remember {
        SignUpViewModel(navController)
    }

    val login = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordRepeat = remember {
        mutableStateOf("")
    }
    val datePicked = remember {
        mutableStateOf("")
    }
    val birthDate = remember {
        mutableStateOf("")
    }
    val isMaleChosen = remember {
        mutableStateOf(false)
    }
    val isFemaleChosen = remember {
        mutableStateOf(false)
    }

    val localContext = LocalContext.current

    val isValidInput = (login.value != "") &&
            (email.value != "") &&
            (name.value != "") &&
            (password.value != "") &&
            (passwordRepeat.value == password.value) &&
            (datePicked.value != "") &&
            (isMaleChosen.value || isFemaleChosen.value)

    val scale = remember {
        Animatable(1f)
    }
    val alpha = remember {
        Animatable(0f)
    }

    val image = painterResource(id = R.drawable.group_57)

    EntryAnimations(scale, alpha)

    Box {
        Image(
            painter = image,
            contentDescription = LocalContext.current.getString(R.string.logo_content_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 106.dp - scale.value * 51.dp)
                .padding(top = 56.dp),
            alignment = Alignment.TopCenter
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(bottom = 14.dp + 44.dp + 8.dp + 38.dp)
                .alpha(alpha.value)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = calculateTopPadding(image)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.sign_up_sign_up_block_text),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                ) {
                    NewOutlinedTextField(
                        login,
                        LocalContext.current.getString(R.string.sign_up_login_text),
                        false
                    )
                    NewOutlinedTextField(
                        email,
                        LocalContext.current.getString(R.string.sign_up_email_text),
                        false
                    )
                    NewOutlinedTextField(
                        name,
                        LocalContext.current.getString(R.string.sign_up_name_text),
                        false
                    )
                    NewOutlinedTextField(
                        password,
                        LocalContext.current.getString(R.string.sign_up_password_text),
                        true
                    )
                    NewOutlinedTextField(
                        passwordRepeat,
                        LocalContext.current.getString(R.string.sign_up_password_confirm_text),
                        true
                    )
                    NewDatePicker(
                        localContext,
                        datePicked,
                        birthDate,
                        LocalContext.current.getString(R.string.sign_up_birth_date_text)
                    )
                    NewGenderCheckField(
                        isMaleChosen = isMaleChosen,
                        isFemaleChosen = isFemaleChosen
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.dp)
                .padding(horizontal = 16.dp)
        ) {
            BottomButtons(
                isValidInput = isValidInput,
                scale.value == 0f,
                localContext,
                signUpViewModel::signUp,
                signUpViewModel::navigateToSignInScreen,
                RegisterRequestBody(
                    login.value,
                    name.value,
                    password.value,
                    email.value,
                    birthDate.value,
                    if (isMaleChosen.value) 0
                    else 1
                )
            )
        }
    }
}


@Composable
private fun EntryAnimations(
    scale: Animatable<Float, AnimationVector1D>,
    alpha: Animatable<Float, AnimationVector1D>
) {
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
    }
}


private fun calculateTopPadding(image: Painter): Dp {

    val screenWidth = Resources.getSystem().displayMetrics.xdpi.dp

    val widthImage = image.intrinsicSize.width.dp
    val heightImage = image.intrinsicSize.height.dp

    val neededWidth = screenWidth - (212.dp * (screenWidth / 360.dp))
    val neededHeight = (heightImage.value * neededWidth.value) / widthImage.value

    val extraPadding = 56.dp + 16.dp

    return neededHeight.dp + extraPadding
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

@Composable
fun BottomButtons(
    isValidInput: Boolean,
    isClickable: Boolean,
    context: Context,
    signUpFun: (scope: CoroutineScope, b: RegisterRequestBody, c: Context) -> Unit,
    navigateToSignInScreenFun: () -> Unit,
    registerRequestBody: RegisterRequestBody
) {

    val scopeRemember = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewOutlinedButton(
            isValidInput,
            LocalContext.current.getString(R.string.sign_up_sign_up_btn_text)
        ) {
            if (isClickable) {
                if (registerRequestBody.userName.trim(' ') == "")
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_login),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (registerRequestBody.email.trim(' ') == "")
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_email),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (!registerRequestBody.email.trim(' ').isEmailValid())
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_email_check),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (registerRequestBody.name.trim(' ') == "")
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_name),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (registerRequestBody.password.trim(' ') == "")
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_password),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (registerRequestBody.password.trim(' ').length < 6)
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_password_length),
                        Toast.LENGTH_SHORT
                    ).show()
                else if (registerRequestBody.birthDate.trim(' ') == "")
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_invalid_birthdate),
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    signUpFun(
                        scopeRemember,
                        registerRequestBody,
                        context
                    )
            }
        }
        Button(
            onClick = {
                navigateToSignInScreenFun()
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
                text = LocalContext.current.getString(R.string.sign_up_sign_in_btn_text),
                fontFamily = ibmPlexSansFamily,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp,
            )
        }
    }
}