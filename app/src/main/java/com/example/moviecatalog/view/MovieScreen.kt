package com.example.moviecatalog.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.ui.theme.montserratFamily
import com.google.accompanist.flowlayout.FlowRow
import java.time.ZonedDateTime

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieScreen(navController: NavController, movieId: Int) {

    val openReviewDialog = remember { mutableStateOf(false) }
    if(openReviewDialog.value)
        ReviewDialog(openReviewDialog)

    val movieGenres = listOf(
        "драма",
        "боевик",
        "фантастик",
        "мелодрама"
    )
    val movieReviews = listOf(
        MovieReview(
            R.drawable.featured,
            "Spike",
            false,
            6,
            "Im the best yeah",
            ZonedDateTime.now()
        ),
        MovieReview(
            R.drawable.profileusericon,
            "Leon",
            true,
            9,
            "Сразу скажу, что фильм мне понравился. Люблю Фримэна, уважаю Роббинса. Читаю Кинга. Но рецензия красненькая.",
            ZonedDateTime.now()
        )
    )

    val state = rememberLazyListState()

    val isTopPanelVisible =
        state.firstVisibleItemIndex != 0 || state.firstVisibleItemScrollOffset.dp >= 200.dp

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            state = state
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
                        H3TextSample(
                            text = "О фильме", Modifier
                                .padding(bottom = 8.dp)
                        )
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
                    H3TextSample(
                        "Жанры", Modifier
                            .padding(bottom = 8.dp)
                    )
                    MovieGenresItems(movieGenres)
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        H3TextSample(
                            "Отзывы", Modifier
                                .padding(bottom = 8.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = "Add review icon",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { openReviewDialog.value = true }
                        )
                    }
                    MovieReviewsItems(movieReviews)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieReviewsItems(MovieReviews: List<MovieReview>) {
    Column(modifier = Modifier) {
        MovieReviews.forEach {
            MovieReviewsItem(it)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieReviewsItem(review: MovieReview) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = review.image),
                        contentDescription = "Reviewers Icon",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                            .size(40.dp)
                    )
                    if (review.isMine) {
                        Column(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(214.dp)
                        ) {
                            H3TextSample(text = review.author, Modifier)
                            SmallGrayTextSample("мой отзыв", Modifier)
                        }
                    } else {
                        H3TextSample(text = review.author, Modifier)
                    }
                }
                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(28.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = review.stars.toString(),
                        textAlign = TextAlign.Center
                    )
                }
            }
            ReviewTextSample(text = review.text)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SmallGrayTextSample(
                    "${review.date.dayOfMonth}." +
                            "${review.date.month.value}." +
                            "${review.date.year}",
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(top = 4.dp)
                )

                if (review.isMine)
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pencil),
                            contentDescription = "Pencil",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.redx),
                            contentDescription = "Red delete button",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
            }
        }
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
private fun H3TextSample(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSecondary
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
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
private fun SmallGrayTextSample(text: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.outline
    )
}

@Composable
private fun ReviewTextSample(text: String) {
    Text(
        text = text,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

data class MovieReview(
    val image: Int,
    val author: String,
    val isMine: Boolean,
    val stars: Int,
    val text: String,
    val date: ZonedDateTime
)