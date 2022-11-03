package com.example.moviecatalog.view.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.ui.theme.montserratFamily
import com.example.moviecatalog.viewmodel.MovieViewModel
import com.example.moviecatalog.viewmodel.ReviewViewModel
import com.google.accompanist.flowlayout.FlowRow
import java.time.ZonedDateTime

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieScreen(movieViewModel: MovieViewModel, movieId: Int) {

    val openReviewDialog = remember { mutableStateOf(false) }
    if (openReviewDialog.value)
        ReviewDialog(openReviewDialog, ReviewViewModel(movieViewModel))

    val movieGenres = remember {
        movieViewModel.movieGenres
    }

    val movieReviews = remember {
        movieViewModel.movieReviews
    }

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
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = movieViewModel.details.name,
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
                    text = movieViewModel.details.description,
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
                            text = LocalContext.current.getString(R.string.about_film_block_text),
                            Modifier
                                .padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        ) {
                            Column {
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_year),
                                    movieViewModel.details.year.toString()
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_country),
                                    movieViewModel.details.country
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_time),
                                    movieViewModel.details.time.toString() + " мин."
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_tagline),
                                    movieViewModel.details.tagline
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_director),
                                    movieViewModel.details.director
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_budget),
                                    "\$" + movieViewModel.details.budget.toString()
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_fees),
                                    "\$" + movieViewModel.details.fees.toString()
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_age_limit),
                                    "${movieViewModel.details.ageLimit}+"
                                )
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
                        LocalContext.current.getString(R.string.genres_block_text), Modifier
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
                            LocalContext.current.getString(R.string.reviews_block_text), Modifier
                                .padding(bottom = 8.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = LocalContext.current.getString(R.string.add_review_icon_content_description),
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
            contentDescription = LocalContext.current.getString(R.string.arrow_back_icon_content_description),
            modifier = Modifier
                .padding(start = 16.dp, top = 45.dp)
                .size(24.dp)
                .clickable { movieViewModel.navigateToMainScreen() }
        )

        if (isTopPanelVisible) {
            TopMovieBar(movieViewModel::navigateToMainScreen, movieViewModel.details.name)
        }
    }
}

@Composable
private fun TopMovieBar(navigateToMainScreenFun: () -> Unit, movieName: String) {
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
            contentDescription = LocalContext.current.getString(R.string.arrow_back_icon_content_description),
            modifier = Modifier
                .size(24.dp)
                .clickable { navigateToMainScreenFun() }
        )
        Text(
            text = movieName,
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
            contentDescription = LocalContext.current.getString(R.string.heart_icon_content_description),
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
                        contentDescription = null,
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
                            SmallGrayTextSample(
                                LocalContext.current.getString(R.string.movie_my_review_text),
                                Modifier
                            )
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
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.redx),
                            contentDescription = null,
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