package ru.dubinin.application.service

import ru.dubinin.application.api.Delegates
import ru.dubinin.application.entity.DolgItem

object DebtService {
    suspend fun getItemsDebt(debtId: Long): List<DolgItem>? {
        val response = Delegates.debtApiService.getDebtItemsById(debtId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            return null
        }
    }
}