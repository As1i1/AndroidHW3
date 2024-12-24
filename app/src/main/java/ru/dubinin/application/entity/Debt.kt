package ru.dubinin.application.entity

data class Debt(
    val id: Long,
    val owner: User,
    val name: String,
    val summary: Long,
    val guests: Long,
    val status: Boolean,
    val date: String,
)