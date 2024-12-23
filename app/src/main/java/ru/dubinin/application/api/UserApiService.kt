package ru.dubinin.application.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.dubinin.application.entity.User

interface UserApiService {
    @GET("v1/user/auth/")
    suspend fun getUser(
        @Query("phoneNumber") phone: String,
        @Query("password") pass: String,
    ): Response<User>

    @GET("v1/user/{id}/")
    suspend fun getUserById(
        @Path("id") userId: Long
    ): Response<User>
}