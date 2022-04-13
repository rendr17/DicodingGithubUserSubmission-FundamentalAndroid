package com.rend.bangkit2022.githubuserapp2.api

import com.rend.bangkit2022.githubuserapp2.data.model.DetailUserResponse
import com.rend.bangkit2022.githubuserapp2.data.model.User
import com.rend.bangkit2022.githubuserapp2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token API TOKEN")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token API TOKEN")
    fun getUserDetail(
        @Path("username") username:String
    ):Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token API TOKEN")
    fun getFollowers(
        @Path("username")username: String
    ):Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token API TOKEN")
    fun getFollowing(
        @Path("username")username: String
    ):Call<ArrayList<User>>
}