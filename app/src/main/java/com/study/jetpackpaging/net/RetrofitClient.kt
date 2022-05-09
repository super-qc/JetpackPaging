package com.study.jetpackpaging.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("http://192.168.50.113/kotlin/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createApi(clazz: Class<T>): T {
        return instance.create(clazz) as T
    }

}