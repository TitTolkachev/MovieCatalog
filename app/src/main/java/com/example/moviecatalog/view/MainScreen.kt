package com.example.moviecatalog.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.navigation.Screen
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavController) {

    val movies: MutableList<Int> = mutableListOf(
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured,
        R.drawable.featured
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 68.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.featured),
                    contentDescription = "Poster",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                Column {

                    Text(
                        text = "Избранное",
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 16.dp, top = 32.dp),
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    //--------------------
                    //--------------------
                    //Переделать на Pager
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .animateItemPlacement()
                    ) {
                        items(movies) { movie ->
                            NewMoviePreview(id = movie)
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Галерея",
                    modifier = Modifier
                        .padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(10) {
                GalleryItem()
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            NewBottomNavigationBar(navController = navController, true)
        }
    }

}


@Composable
private fun GalleryItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.featured),
            contentDescription = "Gallery Item",
            modifier = Modifier
                .width(100.dp)
                .height(144.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 16.dp)
                .defaultMinSize(minHeight = 144.dp)
        ) {
            Column() {
                Text(
                    text = "Люцифер",
                    modifier = Modifier,
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "1999 • США",
                    modifier = Modifier
                        .padding(top = 4.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "драма, криминал",
                    modifier = Modifier
                        .padding(top = 4.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.DarkGray)
                    .width(56.dp)
                    .height(28.dp),
                //contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "9.0",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-1).dp),
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}


@Composable
fun NewBottomNavigationBar(navController: NavController, isMainScreen: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (!isMainScreen)
                    navController.popBackStack()
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor =
                if (isMainScreen)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.outline
            )
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter =
                    if (isMainScreen)
                        painterResource(id = R.drawable.homeiconaccent)
                    else
                        painterResource(id = R.drawable.homeicon),
                    contentDescription = "Main Screen Icon"
                )
                Text(
                    text = "Главная",
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 14.sp
                )
            }
        }
        Button(
            onClick =
            {
                if (isMainScreen) {
                    navController.navigate(Screen.Profile.route)
                }
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor =
                if (isMainScreen)
                    MaterialTheme.colorScheme.outline
                else
                    MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter =
                    if (isMainScreen)
                        painterResource(id = R.drawable.profileicon)
                    else
                        painterResource(id = R.drawable.profileiconacent),
                    contentDescription = "Profile Screen Icon"
                )
                Text(
                    text = "Профиль",
                    fontFamily = ibmPlexSansFamily,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
fun NewMoviePreview(id: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "Movie $id",
        modifier = Modifier
            .padding(start = 16.dp)
            .width(100.dp)
            .height(144.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}