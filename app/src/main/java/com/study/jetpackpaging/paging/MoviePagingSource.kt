package com.study.jetpackpaging.paging

import androidx.paging.PagingSource
import com.study.jetpackpaging.model.Movie
import com.study.jetpackpaging.net.MovieApi
import com.study.jetpackpaging.net.RetrofitClient

class MoviePagingSource : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = 1
        val pageSize = 8
        val movies = RetrofitClient.createApi(MovieApi::class.java).getMovies(currentPage, pageSize)

        var prevKey: Int? = null
        var nextKey: Int? = null
        prevKey = if (currentPage == 1)
            null
        else
            currentPage - 1
        nextKey = if (movies.hasMore) currentPage + 1 else null

        return LoadResult.Page(
            data = movies.movieList,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

}