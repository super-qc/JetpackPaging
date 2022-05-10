package com.study.jetpackpaging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.study.jetpackpaging.model.Movie
import com.study.jetpackpaging.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cache

class MovieViewModel : ViewModel() {

    // cachedIn 缓存flow数据，防止内存泄漏，和viewModel生命周期绑定。旋转手机屏幕后 不会重新请求数据
    private val movies by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 5,
                // 预刷新的距离，距离最后一个item多远时加载数据，默认为pageSize
                prefetchDistance = 1,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { MoviePagingSource() }
        ).flow.cachedIn(viewModelScope)
    }

    fun loadMovie(): Flow<PagingData<Movie>> = movies


}