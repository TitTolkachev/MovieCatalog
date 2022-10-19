package com.example.moviecatalog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun SignInScreen() {

    //ВНИМАНИЕ!!!
    //Переменные ниже нужно будет вынести отдельно!!!
    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val isValidInput = login.value != "" && password.value != ""

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
                .padding(horizontal = 55.dp)
                .padding(top = 56.dp)
                .padding(bottom = 32.dp)
            //contentScale = ContentScale.Crop
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
                //---------------------------------
                //login
                OutlinedTextField(
                    value = login.value,
                    onValueChange = { newText ->
                        login.value = newText
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Логин",
                            fontFamily = ibmPlexSansFamily,
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        placeholderColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        fontFamily = ibmPlexSansFamily,
                        fontSize = 14.sp
                    ),
                    singleLine = true
                )

                //---------------------------------
                //password
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { newText ->
                        password.value = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Пароль",
                            fontFamily = ibmPlexSansFamily,
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        placeholderColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        fontFamily = ibmPlexSansFamily,
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }

            //--------------------------------
            //--------------------------------
            //--------------------------------
            //Кнопки!!!
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    onClick = { },
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
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContentColor = MaterialTheme.colorScheme.primary,
                    )
                ) {
                    Text(
                        text = "Войти"
                    )
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
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
}