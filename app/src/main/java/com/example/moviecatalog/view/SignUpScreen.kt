package com.example.moviecatalog.view

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import java.util.*

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

    val localContext = LocalContext.current

    val isValidInput = (login.value != "") &&
            (email.value != "") &&
            (name.value != "") &&
            (password.value != "") &&
            (passwordRepeat.value == password.value) &&
            (datePicked.value != "")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.group_57),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 106.dp)
                .padding(top = 56.dp)
                .padding(bottom = 16.dp)
            //contentScale = ContentScale.Crop
        )

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
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            LazyColumn(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                item { NewOutlinedTextField(login, "Логин", false) }
//                item { NewOutlinedTextField(email, "E-mail", false) }
//                item { NewOutlinedTextField(name, "Имя", false) }
//                item { NewOutlinedTextField(password, "Пароль", true) }
//                item { NewOutlinedTextField(passwordRepeat, "Подтвердите пароль", true) }
//                item { NewDatePicker(localContext, datePicked) }
//            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewOutlinedTextField(login, "Логин", false)
                NewOutlinedTextField(email, "E-mail", false)
                NewOutlinedTextField(name, "Имя", false)
                NewOutlinedTextField(password, "Пароль", true)
                NewOutlinedTextField(passwordRepeat, "Подтвердите пароль", true)
                NewDatePicker(localContext, datePicked)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewOutlinedButton(isValidInput, "Зарегистрироваться")
                Button(
                    onClick = {
                        navController.popBackStack()
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
            date.value += if (Month < 9) "0${Month+1}." else "${Month+1}."
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