package com.example.moviecatalog.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviecatalog.R
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(navController: NavHostController) {

    //ВНИМАНИЕ!!!
    //Переменные ниже нужно будет вынести отдельно!!!

    val email = remember {
        mutableStateOf("")
    }

    val iconLink = remember {
        mutableStateOf("")
    }

    val name = remember {
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

    val isValidInput = (email.value != "") &&
            (name.value != "") &&
            (iconLink.value != "") &&
            (datePicked.value != "") &&
            (isMaleChosen.value || isFemaleChosen.value)



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 60.dp, bottom = 68.dp + 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profileusericon),
                    contentDescription = "Profile User Icon"
                )
                Text(
                    text = "Leon",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            AboveInputFieldText(text = "E-mail")
            NewOutlinedTextField(email, "", false)
            AboveInputFieldText(text = "Ссылка на аватарку")
            NewOutlinedTextField(iconLink, "", false)
            AboveInputFieldText(text = "Имя")
            NewOutlinedTextField(name, "", false)
            AboveInputFieldText(text = "Дата рождения")
            NewDatePicker(localContext, datePicked, "")
            AboveInputFieldText(text = "Пол")
            NewGenderCheckField(
                isMaleChosen = isMaleChosen,
                isFemaleChosen = isFemaleChosen
            )

            Spacer(
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

            NewOutlinedButton(isValidInput = isValidInput, buttonText = "Сохранить") {
                navController.popBackStack()
                navController.navigate(Screen.Profile.route)
            }
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(Screen.SignIn.route)
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
                    text = "Выйти из аккаунта",
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                )
            }
        }


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            NewBottomNavigationBar(navController = navController, false)
        }
    }
}


@Composable
private fun AboveInputFieldText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .offset(y = (-4).dp)
            .padding(bottom = 4.dp),
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.outline
    )
}
