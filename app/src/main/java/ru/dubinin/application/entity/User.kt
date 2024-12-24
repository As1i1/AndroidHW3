package ru.dubinin.application.entity

data class User(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val bankName: String,
    val image: String?
)
