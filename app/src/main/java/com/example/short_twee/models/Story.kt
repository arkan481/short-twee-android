package com.example.short_twee.models

data class Story(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val user: User
)