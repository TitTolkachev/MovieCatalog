package com.example.moviecatalog.view.sharedsamples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun NewBottomNavigationBar(navFun: () -> Unit, isMainScreen: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { if (!isMainScreen) navFun() }, modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = if (isMainScreen) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline
            )
        ) {
            Column(
                modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = if (isMainScreen) painterResource(id = R.drawable.homeiconaccent)
                    else painterResource(id = R.drawable.homeicon),
                    contentDescription = LocalContext.current.getString(R.string.main_screen_item_content_description)
                )
                Text(
                    text = LocalContext.current.getString(R.string.main_screen_btn_text),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp
                )
            }
        }
        Button(
            onClick = { if (isMainScreen) navFun() },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = if (isMainScreen) MaterialTheme.colorScheme.outline
                else MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = if (isMainScreen) painterResource(id = R.drawable.profileicon)
                    else painterResource(id = R.drawable.profileiconaccent),
                    contentDescription = LocalContext.current.getString(R.string.profile_screen_icon_content_description)
                )
                Text(
                    text = LocalContext.current.getString(R.string.profile_screen_btn_text),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}
