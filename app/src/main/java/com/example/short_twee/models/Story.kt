package com.example.short_twee.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Story(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val user: User
) : Parcelable