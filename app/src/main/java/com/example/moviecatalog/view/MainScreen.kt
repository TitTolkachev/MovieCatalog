package com.example.moviecatalog.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
    }
}

@Composable
fun NewMoviePreview(id: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "Movie $id",
        modifier = Modifier
            .height(144.dp)
            .width(100.dp)
            .padding(start = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}