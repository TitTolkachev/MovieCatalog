package com.example.moviecatalog.view.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.util.DEFAULT_IMAGE
import com.example.moviecatalog.util.loadPicture
import com.example.moviecatalog.view.sharedsamples.*
import com.example.moviecatalog.viewmodel.ProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(navController: NavController) {

    val profileViewModel = remember {
        ProfileViewModel(navController)
    }

    val rememberScope = rememberCoroutineScope()

    val email = remember {
        mutableStateOf(profileViewModel.user.value.email)
    }

    val avatarLink = remember {
        mutableStateOf(profileViewModel.user.value.avatarLink)
    }

    val name = remember {
        mutableStateOf(profileViewModel.user.value.name)
    }

    val birthDate = remember {
        mutableStateOf(profileViewModel.user.value.birthDate)
    }

    val isMaleChosen = remember {
        mutableStateOf(profileViewModel.user.value.gender == 0)
    }

    val isFemaleChosen = remember {
        mutableStateOf(profileViewModel.user.value.gender == 1)
    }

    val callInitFunctions = remember {
        mutableStateOf(true)
    }
    if (callInitFunctions.value) {
        profileViewModel.getProfileDetails(
            rememberScope,
            LocalContext.current,
            email,
            avatarLink,
            name,
            birthDate,
            isMaleChosen,
            isFemaleChosen
        )
        callInitFunctions.value = false
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
                val image = loadPicture(
                    url = profileViewModel.user.value.avatarLink, LocalContext.current,
                    defaultImage = DEFAULT_IMAGE
                ).value
                if (image != null) {
                    Image(
                        contentScale = ContentScale.Crop,
                        bitmap = image.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(88.dp)
                    )
                }
                Text(
                    text = profileViewModel.user.value.nickName.toString(),
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


