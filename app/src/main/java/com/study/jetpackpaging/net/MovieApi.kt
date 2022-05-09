package com.study.jetpackpaging.net

import com.study.jetpackpaging.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("pkds.php")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("pagesize")pageSize:Int
    ): Movies
}