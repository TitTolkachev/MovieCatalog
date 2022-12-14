package com.example.moviecatalog.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moviecatalog.R
import com.example.moviecatalog.network.dataclasses.models.ReviewModifyModel
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily
import com.example.moviecatalog.ui.theme.montserratFamily
import com.example.moviecatalog.viewmodel.MovieViewModel
import com.example.moviecatalog.viewmodel.ReviewViewModel

@ExperimentalMaterial3Api
@Composable
fun ReviewDialog(
    openReviewDialog: MutableState<Boolean>,
    reviewViewModel: ReviewViewModel,
    movieViewModel: MovieViewModel,
    movieId: String,
    myReview: MutableState<ReviewModifyModel>,
    reviewId: MutableState<String?>
) {

    val reviewText = remember {
        mutableStateOf(myReview.value.reviewText)
    }
    val isAnonymous = remember {
        mutableStateOf(myReview.value.isAnonymous)
    }
    val rating = remember {
        mutableStateOf(myReview.value.rating)
    }

    val rememberScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = { openReviewDialog.value = false },
    ) {
        Surface(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.review_dialog_add_review_text),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyRow(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(10) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { rating.value = it + 1 },
                                painter = painterResource(
                                    id = if (it < rating.value)
                                        R.drawable.filled_star
                                    else
                                        R.drawable.unfilled_star
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }

                TextField(
                    value = reviewText.value,
                    placeholder = {
                        Text(LocalContext.current.getString(R.string.review_dialog_review_text))
                    },
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(6.dp),
                    onValueChange = { reviewText.value = it },
                    textStyle = TextStyle(
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontSize = 14.sp,
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedIndicatorColor = Color.Transparent,
                        textColor = MaterialTheme.colorScheme.background,
                        containerColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    maxLines = 10
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.review_dialog_anonymous_check_text),
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Checkbox(
                            modifier = Modifier
                                .size(24.dp),
                            checked = isAnonymous.value,
                            onCheckedChange = { isAnonymous.value = it },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = Color.Transparent,
                                checkedColor = Color.Transparent,
                                checkmarkColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                Button(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    onClick = {
                        if(myReview.value.reviewText == "")
                            reviewViewModel.addReview(rememberScope, movieId, ReviewModifyModel(reviewText.value, rating.value, isAnonymous.value))
                        else
                            reviewViewModel.editReview(rememberScope, movieId, reviewId.value!!, ReviewModifyModel(reviewText.value, rating.value, isAnonymous.value))
                        openReviewDialog.value = false
                        movieViewModel.updateMovieScreen(movieId)
                    }) {
                    Text(
                        text = LocalContext.current.getString(R.string.review_dialog_save_btn_text),
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Button(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    onClick = { openReviewDialog.value = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.review_dialog_cancel_btn_text),
                        fontFamily = ibmPlexSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


