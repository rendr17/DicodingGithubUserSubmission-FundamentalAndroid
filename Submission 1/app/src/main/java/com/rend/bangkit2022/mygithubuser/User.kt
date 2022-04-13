package com.rend.bangkit2022.mygithubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val username: String,
    val name: String,
    val avatar: Int,
    val company: String,
    val location: String,
    val repository: String,
    val followers: String,
    val following: String
) : Parcelable
