package com.example.moviecatalog.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.ui.theme.montserratFamily

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieScreen(navController: NavController, movieId: Int) {


    val state = rememberLazyListState()

    val isTopPanelVisible = state.firstVisibleItemIndex != 0 || state.firstVisibleItemScrollOffset.dp >= 200.dp

    LazyColumn(
        state = state,
        modifier = Modifier
    ) {
        item {
            Box {
                Image(
                    painter = painterResource(id = movieId),
                    contentDescription = "Movie poster",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
//                Image(
//                    painter = painterResource(id = R.drawable.arrow_back),
//                    contentDescription = "Arrow back",
//                    modifier = Modifier
//                        .padding(start = 16.dp, top = 45.dp)
//                        .size(24.dp)
//                        .clickable { navController.popBackStack() }
//                )
                Text(
                    text = "Побег из Шоушенка",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    maxLines = 4
                )
            }
        }
        item {
            Surface(color = MaterialTheme.colorScheme.background) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения",
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        item {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column {
                    Text(
                        text = "О фильме",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth(),
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        Column {
                            AboutMovieRowSample("Год", "1994")
                            AboutMovieRowSample("Страна", "США")
                            AboutMovieRowSample("Время", "142 мин.")
                            AboutMovieRowSample(
                                "Слоган",
                                "«Страх - это кандалы. Надежда - это свобода»"
                            )
                            AboutMovieRowSample("Режиссёр", "Фрэнк Дарабонт")
                            AboutMovieRowSample("Бюджет", "\$25 000 000")
                            AboutMovieRowSample("Сборы в мире", "\$28 418 687")
                            AboutMovieRowSample("Возраст", "16+")
                        }
                    }
                    Spacer(modifier = Modifier.height(500.dp))
                }
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.arrow_back),
        contentDescription = "Arrow back",
        modifier = Modifier
            .padding(start = 16.dp, top = 45.dp)
            .size(24.dp)
            .clickable { navController.popBackStack() }
    )

    if (isTopPanelVisible) {
        TopMovieBar(navController)
    }
}

@Composable
private fun TopMovieBar(navController: NavController) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .height(80.dp)
            .padding(top = 34.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Arrow back",
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.popBackStack() }
        )
        Text(
            text = "Побег из Шоушенка",
            modifier = Modifier
                .width(263.dp)
                .offset(y = (-2).dp),
            fontFamily = ibmPlexSansFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLines = 1
        )
        Image(
            painter = painterResource(id = R.drawable.unfilled_heart),
            contentDescription = "Heart",
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Composable
private fun AboutMovieRowSample(text1: String, text2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        GrayTextSample(text = text1)
        WhiteTextSample(text = text2)
    }
}

@Composable
private fun GrayTextSample(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .width(100.dp)
            .padding(bottom = 4.dp, end = 8.dp),
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onTertiary
    )
}

@Composable
private fun WhiteTextSample(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(bottom = 4.dp),
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSecondary
    )
}