package com.rend.bangkit2022.githubuserapp2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserFavoriteDao {
    @Insert
    suspend fun addFavorite(userFavorite: UserFavorite)

    @Query("select * from favorite_user")
    fun getFavoriteUser():LiveData<List<UserFavorite>>

    @Query("select count(*) from favorite_user where favorite_user.id = :id")
    suspend fun checkUser(id: Int):Int

    @Query("delete from favorite_user where favorite_user.id = :id")
    suspend fun removeFavorite(id: Int): Int
}