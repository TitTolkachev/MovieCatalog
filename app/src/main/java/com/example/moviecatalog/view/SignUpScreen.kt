package com.example.moviecatalog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@ExperimentalMaterial3Api
@Composable
fun SignUpScreen() {

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

    val isValidInput = login.value != "" &&
            email.value != "" &&
            name.value != "" &&
            password.value != "" &&
            passwordRepeat.value != ""

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewOutlinedTextField(login, "Логин", false)
                NewOutlinedTextField(email, "E-mail", false)
                NewOutlinedTextField(name, "Имя", false)
                NewOutlinedTextField(password, "Пароль", true)
                NewOutlinedTextField(passwordRepeat, "Подтвердите пароль", true)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewOutlinedButton(isValidInput, "Зарегистрироваться")
                Button(
                    onClick = { },
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