package ru.dubinin.application.service

import ru.dubinin.application.api.Delegates
import ru.dubinin.application.entity.Debt
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

    suspend fun getDebts(userId: Long): List<Debt>? {
        val response = Delegates.debtApiService.getDebtByUserId(userId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            return null
        }
    }
}