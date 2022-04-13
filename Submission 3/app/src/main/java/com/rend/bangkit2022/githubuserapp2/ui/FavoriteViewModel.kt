package com.rend.bangkit2022.githubuserapp2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rend.bangkit2022.githubuserapp2.data.local.UserDatabase
import com.rend.bangkit2022.githubuserapp2.data.local.UserFavorite
import com.rend.bangkit2022.githubuserapp2.data.local.UserFavoriteDao

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: UserFavoriteDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.userFavoriteDao()
    }

    fun getFavoriteUser(): LiveData<List<UserFavorite>>?{
        return userDao?.getFavoriteUser()
    }
}