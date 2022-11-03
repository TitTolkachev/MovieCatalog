package com.example.moviecatalog.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.theme.ibmPlexSansFamily

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.times
import com.example.moviecatalog.view.sharedsamples.NewBottomNavigationBar
import com.example.moviecatalog.viewmodel.MainViewModel
import kotlin.math.absoluteValue

@SuppressLint("FrequentlyChangedStateReadInComposition")
@ExperimentalFoundationApi
@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val favouriteMovies = remember {
        mainViewModel.favouriteMovies
    }

    val favouriteItems = remember {
        favouriteMovies.mapIndexed { i, s -> ImageItem(i, s) }.toMutableStateList()
    }

    val favouritesState = rememberLazyListState()

    val galleryMovies = remember {
        mutableStateOf(mainViewModel.galleryMovies)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 68.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.featured),
                    contentDescription = LocalContext.current.getString(R.string.poster_content_description),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Column {
                    if (favouriteItems.size != 0)
                        Text(
                            text = LocalContext.current.getString(R.string.favourites_block_text),
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 16.dp, top = 32.dp),
                            fontFamily = ibmPlexSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 22.dp)
                            .animateItemPlacement()
                            .height(
                                if (favouriteItems.size != 0)
                                    172.dp
                                else
                                    0.dp
                            ),
                        state = favouritesState,
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(favouriteItems, key = { it.id }) { item ->
                            val value =
                                (1.2F - (favouritesState.layoutInfo.normalizedItemPosition(
                                    item.id
                                ).absoluteValue) * 0.05F)
                                    .coerceAtMost(1.2F)
                                    .coerceAtLeast(1F)

                            NewMoviePreview(
                                item.imageId,
                                Modifier
                                    .graphicsLayer {
                                        scaleX = value
                                        scaleY = value
                                    }
                                    .padding(horizontal = ((value - 1F) * 50.dp) + 8.dp),
                                mainViewModel::removeMovieFromFavourites,
                                favouriteItems,
                                item,
                                mainViewModel::navigateToMovie
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = LocalContext.current.getString(R.string.gallery_block_text),
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(galleryMovies.value) { item ->
                GalleryItem(item, mainViewModel::navigateToMovie)
            }
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            NewBottomNavigationBar(mainViewModel::navigateToProfileScreen, true)
        }
    }
}


@Composable
private fun GalleryItem(item: Int, navigateToMovieFun: (image: Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .clickable {
                navigateToMovieFun(item)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.featured),
            contentDescription = LocalContext.current.getString(R.string.gallery_item_content_description),
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
            Column {
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
                    modifier = Modifier.padding(top = 4.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "драма, криминал",
                    modifier = Modifier.padding(top = 4.dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.DarkGray)
                    .width(56.dp)
                    .height(28.dp)
            ) {
                Text(
                    text = "9.0",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-1).dp),
                    fontFamily = ibmPlexSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}


@Composable
fun NewMoviePreview(
    id: Int,
    modifier: Modifier = Modifier,
    removeMovieFun: (image: Int) -> Unit,
    items: SnapshotStateList<ImageItem>,
    item: ImageItem,
    navigateToMovieFun: (image: Int) -> Unit
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = id),
            contentDescription = LocalContext.current.getString(R.string.movie_preview_content_description) + id.toString(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(144.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable {
                    navigateToMovieFun(item.imageId)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.deletefromfavourites),
            contentDescription = LocalContext.current.getString(R.string.delete_from_favourites_icon_content_description),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 4.dp, end = 4.dp)
                .clickable {
                    removeMovieFun(item.imageId)
                    items.remove(item)
                }
        )
    }
}

fun LazyListLayoutInfo.normalizedItemPosition(key: Any): Float =
    visibleItemsInfo.firstOrNull { it.key == key }?.let {
        val center = 60F
        (it.offset.toFloat() - center) / center
    } ?: 0F

data class ImageItem(val id: Int, val imageId: Int)