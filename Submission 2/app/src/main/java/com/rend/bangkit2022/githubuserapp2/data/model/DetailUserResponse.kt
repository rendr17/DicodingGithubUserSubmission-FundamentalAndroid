package com.rend.bangkit2022.githubuserapp2.data.model

data class DetailUserResponse(
    val login:String,
    val id:Int,
    val avatar_url:String,
    val followers_url:String,
    val following_url:String,
    val name:String,
    val company: String,
    val location: String,
    val public_repos: String,
    val followers:Int,
    val following:Int
)
