package com.example.moviecatalog.view.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecatalog.R
import com.example.moviecatalog.network.dataclasses.models.*
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.ui.theme.montserratFamily
import com.example.moviecatalog.util.DEFAULT_IMAGE
import com.example.moviecatalog.util.DEFAULT_PROFILE_IMAGE
import com.example.moviecatalog.util.loadPicture
import com.example.moviecatalog.viewmodel.MovieViewModel
import com.example.moviecatalog.viewmodel.ReviewViewModel
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.*
import kotlin.reflect.KFunction2

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieScreen(navController: NavController, movieId: String) {

    val movieViewModel = remember {
        MovieViewModel(navController)
    }

    val context = LocalContext.current
    val rememberScope = rememberCoroutineScope()

    val details = remember {
        mutableStateOf(
            MovieDetailsModel(
                id = "2",
                year = 2000,
                time = 120,
                ageLimit = 16
            )
        )
    }
    val user = remember {
        mutableStateOf(
            ProfileModel(
                id = "1",
                nickName = "123",
                email = "123",
                avatarLink = "123",
                name = "123",
                birthDate = "123",
                gender = 0
            )
        )
    }

    val myReview = remember {
        mutableStateOf(
            ReviewModifyModel(
                "",
                0,
                false
            )
        )
    }

    val myReviewId = remember {
        mutableStateOf(null as String?)
    }

    val isMovieLiked = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        movieViewModel.checkIsMovieInFavourites(isMovieLiked, movieId, rememberScope)
    }

    LaunchedEffect(key1 = true)
    {
        launch(Dispatchers.Main) {
            movieViewModel.getUser(rememberScope, user, context)
            movieViewModel.getMovieDetails(
                rememberScope,
                context,
                movieId,
                details,
                user,
                myReview,
                myReviewId
            )
        }
    }

    val openReviewDialog = remember { mutableStateOf(false) }
    if (openReviewDialog.value)
        ReviewDialog(
            openReviewDialog,
            ReviewViewModel(),
            movieViewModel,
            movieId,
            myReview,
            myReviewId
        )

    val state = rememberLazyListState()

    val isTopPanelVisible =
        state.firstVisibleItemIndex != 0 || state.firstVisibleItemScrollOffset.dp >= 400.dp

    val alpha = 1f - state.firstVisibleItemScrollOffset.dp / 250.dp

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            state = state
        ) {
            item {
                Box {
                    val image = loadPicture(
                        url = details.value.poster.toString(), LocalContext.current,
                        defaultImage = DEFAULT_IMAGE
                    ).value
                    if (image != null) {
                        Image(
                            bitmap = image.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.Crop,
                            alpha = if (state.firstVisibleItemIndex != 0)
                                0f
                            else
                                alpha
                        )
                    }
                    details.value.name?.let {
                        Text(
                            text = it,
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
            }
            item {
                details.value.description?.let {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = it,
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
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
                                    details.value.year.toString()
                                )
                                details.value.country?.let {
                                    AboutMovieRowSample(
                                        LocalContext.current.getString(R.string.movie_country),
                                        it
                                    )
                                }
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_time),
                                    details.value.time.toString() + " ??????."
                                )
                                details.value.tagline?.let {
                                    AboutMovieRowSample(
                                        LocalContext.current.getString(R.string.movie_tagline),
                                        it
                                    )
                                }
                                details.value.director?.let {
                                    AboutMovieRowSample(
                                        LocalContext.current.getString(R.string.movie_director),
                                        it
                                    )
                                }
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_budget),
                                    "\$" + details.value.budget.toString()
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_fees),
                                    "\$" + details.value.fees.toString()
                                )
                                AboutMovieRowSample(
                                    LocalContext.current.getString(R.string.movie_age_limit),
                                    "${details.value.ageLimit}+"
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
                    if (details.value.genres != null) {
                        MovieGenresItems(details.value.genres!!)
                    }
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
                        if (!movieViewModel.myReviewExists.value)
                            Image(
                                painter = painterResource(id = R.drawable.plus_icon),
                                contentDescription = LocalContext.current.getString(R.string.add_review_icon_content_description),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { openReviewDialog.value = true }
                            )
                    }
                    MovieReviewsItems(
                        details.value.reviews,
                        user.value,
                        movieViewModel,
                        movieId,
                        rememberScope,
                        openReviewDialog
                    )
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
            details.value.name?.let {
                TopMovieBar(
                    movieViewModel::navigateToMainScreen,
                    if (!isMovieLiked.value) movieViewModel::addMovieToFavourites
                    else movieViewModel::deleteMovieFromFavourites,
                    movieId,
                    rememberScope,
                    it,
                    isMovieLiked
                )
            }
        }
    }
}

@Composable
private fun TopMovieBar(
    navigateToMainScreenFun: () -> Unit,
    movieFun: KFunction2<String, CoroutineScope, Unit>,
    movieId: String,
    coroutineScope: CoroutineScope,
    movieName: String,
    isMovieLiked: MutableState<Boolean>
) {
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
        Box(
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    movieFun(movieId, coroutineScope)
                    isMovieLiked.value = !isMovieLiked.value
                }) {
            Image(
                painter = painterResource(
                    id = if (isMovieLiked.value)
                        R.drawable.heart
                    else
                        R.drawable.unfilled_heart
                ),
                contentDescription =
                LocalContext.current.getString(R.string.heart_icon_content_description),
                modifier = Modifier
                    .width(20.dp)
                    .height(18.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieReviewsItems(
    MovieReviews: MutableList<ReviewModel>?,
    user: ProfileModel,
    movieViewModel: MovieViewModel,
    movieId: String,
    coroutineScope: CoroutineScope,
    openReviewDialog: MutableState<Boolean>
) {
    Column(modifier = Modifier) {
        MovieReviews?.forEach {
            MovieReviewsItem(it, user, movieViewModel, movieId, coroutineScope, openReviewDialog)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieReviewsItem(
    review: ReviewModel,
    user: ProfileModel,
    movieViewModel: MovieViewModel,
    movieId: String,
    coroutineScope: CoroutineScope,
    openReviewDialog: MutableState<Boolean>
) {
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
                    if (review.author.avatar == "" || review.isAnonymous) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.profileusericon),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clip(CircleShape)
                                .size(40.dp)
                        )
                    } else {
                        val image = review.author.avatar?.let {
                            loadPicture(
                                url = it, LocalContext.current,
                                defaultImage = DEFAULT_PROFILE_IMAGE
                            ).value
                        }
                        if (image != null) {
                            Image(
                                contentScale = ContentScale.Crop,
                                bitmap = image.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clip(CircleShape)
                                    .size(40.dp)
                            )
                        }
                    }
                    if (review.author.userId == user.id) {
                        Column(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .width(214.dp)
                        ) {
                            if (review.isAnonymous)
                                H3TextSample(
                                    text = LocalContext.current.getString(R.string.movie_anonymous_review_author),
                                    Modifier
                                )
                            else
                                review.author.nickName?.let { H3TextSample(text = it, Modifier) }
                            SmallGrayTextSample(
                                LocalContext.current.getString(R.string.movie_my_review_text),
                                Modifier
                            )
                        }
                    } else if (review.isAnonymous)
                        H3TextSample(
                            text = LocalContext.current.getString(R.string.movie_anonymous_review_author),
                            Modifier
                        )
                    else
                        review.author.nickName?.let { H3TextSample(text = it, Modifier) }
                }
                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(28.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(calculateColorFun(review.rating.toFloat()))
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = review.rating.toString(),
                        textAlign = TextAlign.Center
                    )
                }
            }
            review.reviewText?.let { ReviewTextSample(text = it) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val year = review.createDateTime.subSequence(0, 4)
                val month = review.createDateTime.subSequence(5, 7)
                val day = review.createDateTime.subSequence(8, 10)
                SmallGrayTextSample(
                    "${day}.${month}.${year}",
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(top = 4.dp)
                )

                if (review.author.userId == user.id)
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
                                .clickable {
                                    openReviewDialog.value = true
                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.redx),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    movieViewModel.deleteReview(coroutineScope, movieId, review.id)
                                    movieViewModel.updateMovieScreen(movieId)
                                }
                        )
                    }
            }
        }
    }
}

@Composable
private fun MovieGenresItems(movieGenres: MutableList<GenreModel>) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp
    ) {
        movieGenres.forEach {
            it.name?.let { it1 -> MovieGenreItem(text = it1) }
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