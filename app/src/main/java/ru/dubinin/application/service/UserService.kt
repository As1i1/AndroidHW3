package ru.dubinin.application.service

import android.content.Context
import android.widget.Toast
import ru.dubinin.application.api.Delegates
import ru.dubinin.application.entity.User

object UserService {

    suspend fun getUser(context: Context, phone: String, password: String): User? {
        try {
            val response = Delegates.userApiService.getUser(phone, password)
            return if (response.isSuccessful) {
                response.body()
            } else {
                return null
            }
        } catch (e: Exception) {
            val err = Toast.makeText(context, "Server error", Toast.LENGTH_SHORT)
            err.show()
        }
        return null
    }

    suspend fun getUserById(context: Context, id: Long): User? {
        try {
            val response = Delegates.userApiService.getUserById(id)
            return if (response.isSuccessful) {
                response.body()
            } else {
                return null
            }
        } catch (e: Exception) {
            val err = Toast.makeText(context, "Server error", Toast.LENGTH_SHORT)
            err.show()
        }
        return null
    }
}