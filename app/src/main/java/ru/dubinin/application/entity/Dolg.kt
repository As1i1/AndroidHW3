package ru.dubinin.application.entity

data class Dolg(
    val id: Long,
    val title: String,
    val authorName: String,
    val summary: Long,
    val peopleCount: Long,
    val status: Boolean,
)