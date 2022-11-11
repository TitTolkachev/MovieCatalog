package com.example.moviecatalog.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviecatalog.network.dataclasses.models.MovieElementModel
import com.example.moviecatalog.network.dataclasses.models.PageInfoModel
import com.example.moviecatalog.network.dataclasses.responses.MoviesResponse
import com.example.moviecatalog.network.movie.MovieRepository
import okio.IOException
import retrofit2.HttpException

private val movieRepository = MovieRepository()

class MovieSource : PagingSource<Int, MovieElementModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieElementModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieElementModel> {
        return try {
            val nextPage = params.key ?: 1
            var userList = MoviesResponse(PageInfoModel(0, 0, 0), mutableListOf())

            movieRepository.getMovies(page = nextPage)
                .collect { result ->
                    result.onSuccess {
                        userList = it
                    }
                }

            LoadResult.Page(
                data = userList.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.pageInfo.currentPage == userList.pageInfo.pageCount) null else userList.pageInfo.currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}