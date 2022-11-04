package com.example.moviecatalog.view.screens

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.view.sharedsamples.*
import com.example.moviecatalog.viewmodel.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {

    val rememberScope = rememberCoroutineScope()

    profileViewModel.getProfileDetails(rememberScope, LocalContext.current)
    val user = remember {
        profileViewModel.user
    }

    val email = remember {
        mutableStateOf(user.email)
    }

    val avatarLink = remember {
        mutableStateOf(user.avatarLink!!)
    }

    val name = remember {
        mutableStateOf(user.name)
    }

    val birthDate = remember {
        mutableStateOf(user.birthDate)
    }

    val isMaleChosen = remember {
        mutableStateOf(user.gender == 0)
    }

    val isFemaleChosen = remember {
        mutableStateOf(user.gender == 1)
    }

    val localContext = LocalContext.current

    val isValidInput = (email.value != "") &&
            (name.value != "") &&
            (avatarLink.value != "") &&
            (birthDate.value != "") &&
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
                    contentDescription = null
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

            AboveInputFieldText(text = LocalContext.current.getString(R.string.profile_email))
            NewOutlinedTextField(email, "", false)
            AboveInputFieldText(text = LocalContext.current.getString(R.string.profile_avatar_link))
            NewOutlinedTextField(avatarLink, "", false)
            AboveInputFieldText(text = LocalContext.current.getString(R.string.profile_name))
            NewOutlinedTextField(name, "", false)
            AboveInputFieldText(text = LocalContext.current.getString(R.string.profile_birth_date))
            NewDatePicker(localContext, birthDate, "")
            AboveInputFieldText(text = LocalContext.current.getString(R.string.profile_gender))
            NewGenderCheckField(
                isMaleChosen = isMaleChosen,
                isFemaleChosen = isFemaleChosen
            )

            Spacer(
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

            NewOutlinedButton(
                isValidInput = isValidInput,
                buttonText = LocalContext.current.getString(R.string.profile_save_btn_text)
            ) {
                profileViewModel.saveProfileChanges()
            }
            Button(
                onClick = {
                    profileViewModel.navigateToSignInScreen()
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
                    text = LocalContext.current.getString(R.string.profile_sign_out_btn_text),
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
            NewBottomNavigationBar(profileViewModel::navigateToMainScreen, false)
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


