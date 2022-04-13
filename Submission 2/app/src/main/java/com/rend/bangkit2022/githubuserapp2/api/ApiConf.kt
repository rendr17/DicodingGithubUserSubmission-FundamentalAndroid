package com.rend.bangkit2022.githubuserapp2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConf {
    private const val BASE_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}