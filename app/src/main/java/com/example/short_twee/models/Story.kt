package com.example.short_twee.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Story(
    val id: String? = null,
    var title: String? = null,
    var content: String? = null,
    val createdAt: String? = null,
    val user: User? = null
) : Parcelable