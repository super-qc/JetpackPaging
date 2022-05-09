package com.study.jetpackpaging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.study.jetpackpaging.model.Movie
import com.study.jetpackpaging.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    fun loadMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 8
            ),
            pagingSourceFactory = { MoviePagingSource() }
        ).flow
    }


}