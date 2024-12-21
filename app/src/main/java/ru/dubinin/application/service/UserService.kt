package ru.dubinin.application.service

import ru.dubinin.application.api.Delegates
import ru.dubinin.application.entity.User

object UserService {

    suspend fun getUser(phone: String, password: String): User? {
        val response = Delegates.userApiService.getUser(phone, password)
        return if (response.isSuccessful) {
            response.body()
        } else {
            return null
        }
    }
}