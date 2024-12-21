package ru.dubinin.application.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.dubinin.application.entity.Debt
import ru.dubinin.application.entity.DolgItem

interface DebtApiService {
    @GET("v1/debt/{id}/items/")
    suspend fun getDebtItemsById(
        @Path("id") id: Long
    ): Response<List<DolgItem>>

    @GET("v1/user/{id}/debt/")
    suspend fun getDebtByUserId(
        @Path("id") id: Long
    ): Response<List<Debt>>
}