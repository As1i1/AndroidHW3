package ru.dubinin.application.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Delegates {
    const val URL = "http://172.28.26.146:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    val debtApiService: DebtApiService by lazy {
        retrofit.create(DebtApiService::class.java)
    }

}