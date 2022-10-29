package com.example.moviecatalog.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.google.accompanist.flowlayout.FlowRow

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieScreen(navController: NavController, movieId: Int) {

    val movieGenres = listOf(
        "драма",
        "боевик",
        "фантастик",
        "мелодрама"
    )

    val state = rememberLazyListState()

    val isTopPanelVisible =
        state.firstVisibleItemIndex != 0 || state.firstVisibleItemScrollOffset.dp >= 200.dp

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            state = state,
            modifier = Modifier
                .padding(bottom = 16.dp)
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
            item {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        H3TextSample(text = "О фильме")
                        Row(
                            modifier = Modifier
                                .padding(bottom = 16.dp)
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
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    H3TextSample("Жанры")
                    MovieGenresItems(movieGenres)
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        H3TextSample("Отзывы")
                        Image(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = "Add review icon",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                    MovieGenresItems(movieGenres)
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
private fun MovieGenresItems(movieGenres: List<String>) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        movieGenres.forEach {
            MovieGenreItem(text = it)
        }
    }
}

@Composable
private fun H3TextSample(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(bottom = 8.dp),
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
private fun MovieGenreItem(text: String) {
    Text(
        text = text,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.tertiary,
        maxLines = 1,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp)
            .padding(vertical = 6.dp)
    )
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