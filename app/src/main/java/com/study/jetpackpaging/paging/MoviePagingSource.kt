package com.study.jetpackpaging.paging

import android.util.Log
import androidx.paging.PagingSource
import com.study.jetpackpaging.model.Movie
import com.study.jetpackpaging.net.MovieApi
import com.study.jetpackpaging.net.RetrofitClient
import kotlinx.coroutines.delay
import java.lang.Exception

class MoviePagingSource : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        // 调试用
        delay(2000)

        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        val movies = RetrofitClient.createApi(MovieApi::class.java).getMovies(currentPage, pageSize)

        Log.d("paging3", "currentPage:${currentPage},pageSize:$pageSize")

        var prevKey: Int? = null
        var nextKey: Int? = null

        var realPageSize = 5
        val initialLoadSize = 10

        if (currentPage == 1) {
            prevKey = null
            nextKey = if (movies.hasMore) initialLoadSize / realPageSize + 1 else null
        } else {
            prevKey = currentPage - 1
            nextKey = if (movies.hasMore) currentPage + 1 else null
        }

        /*
        prevKey = if (currentPage == 1)
            null
        else
            currentPage - 1
        nextKey = if (movies.hasMore) currentPage + 1 else null
        */

        Log.d("paging3", "prevKey:$prevKey,nextKey:$nextKey")

        return try {
            LoadResult.Page(
                data = movies.movieList,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

}