package com.example.moviecatalog.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.Screen
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*

@DelicateCoroutinesApi
@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController) {

    //ВНИМАНИЕ!!!
    //Переменные ниже нужно будет вынести отдельно!!!
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
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 106.dp - scale.value * 51.dp)
                .padding(top = 56.dp),
            alignment = Alignment.TopCenter
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(bottom = 32.dp + 44.dp + 8.dp + 32.dp)
                .alpha(alpha.value)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = calculateTopPadding(image)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Регистрация",
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
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    NewOutlinedTextField(login, "Логин", false)
                    NewOutlinedTextField(email, "E-mail", false)
                    NewOutlinedTextField(name, "Имя", false)
                    NewOutlinedTextField(password, "Пароль", true)
                    NewOutlinedTextField(passwordRepeat, "Подтвердите пароль", true)
                    NewDatePicker(localContext, datePicked)
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
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            BottomButtons(
                isValidInput = isValidInput,
                navController = navController,
                scale.value == 0f
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
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            )
        )
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 700,
                easing = FastOutLinearInEasing
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


@Composable
fun BottomButtons(isValidInput: Boolean, navController: NavController, isClickable: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewOutlinedButton(
            isValidInput,
            "Зарегистрироваться"
        ) {
            navController.popBackStack(Screen.SignIn.route, true)
            navController.navigate(Screen.Main.route)
        }

        Button(
            onClick = {
                if (isClickable)
                    navController.navigate(Screen.SignIn.route + "/${true}")
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
                text = "У меня уже есть аккаунт"
            )
        }
    }
}


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NewDatePicker(context: Context, date: MutableState<String>) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, Year: Int, Month: Int, DayOfMonth: Int ->
            date.value = if (DayOfMonth < 10) "0$DayOfMonth." else "$DayOfMonth."
            date.value += if (Month < 9) "0${Month + 1}." else "${Month + 1}."
            date.value += Year.toString()
        }, year, month, day
    )

    OutlinedButton(
        onClick = {
            datePickerDialog.show()
        },
        enabled = true,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .height(52.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (date.value == "") "Дата рождения" else date.value,
                fontFamily = ibmPlexSansFamily,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Start,
                color = if (date.value == "") MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
            )
            Icon(
                painter = painterResource(id = R.drawable.calendaricon),
                contentDescription = "Calendar Icon",
                modifier = Modifier
                    .scale(1.1f)
                    .padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}


@Composable
fun NewGenderCheckField(
    isMaleChosen: MutableState<Boolean>,
    isFemaleChosen: MutableState<Boolean>
) {

    Row(modifier = Modifier) {
        OutlinedButton(
            onClick = {
                isMaleChosen.value = true
                isFemaleChosen.value = false
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isMaleChosen.value) MaterialTheme.colorScheme.primary else Color.Transparent
            ),
            modifier = Modifier
                .height(44.dp)
                .weight(1f)
                .offset(x = 0.5.dp),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 0.dp,
                bottomStart = 8.dp,
                bottomEnd = 0.dp
            )
        ) {
            Text(
                text = "Мужчина",
                fontFamily = ibmPlexSansFamily,
                fontSize = 14.sp,
                modifier = Modifier,
                color = if (isMaleChosen.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        }

        OutlinedButton(
            onClick = {
                isMaleChosen.value = false
                isFemaleChosen.value = true
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isFemaleChosen.value) MaterialTheme.colorScheme.primary else Color.Transparent
            ),
            modifier = Modifier
                .height(44.dp)
                .weight(1f)
                .offset(x = ((-0.5).dp)),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 8.dp
            )
        ) {
            Text(
                text = "Женщина",
                fontFamily = ibmPlexSansFamily,
                fontSize = 14.sp,
                modifier = Modifier,
                color = if (isFemaleChosen.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
            )
        }
    }

}