package com.rend.bangkit2022.githubuserapp2.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rend.bangkit2022.githubuserapp2.api.ApiConf
import com.rend.bangkit2022.githubuserapp2.data.local.UserDatabase
import com.rend.bangkit2022.githubuserapp2.data.local.UserFavorite
import com.rend.bangkit2022.githubuserapp2.data.local.UserFavoriteDao
import com.rend.bangkit2022.githubuserapp2.data.model.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {

    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: UserFavoriteDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.userFavoriteDao()

    }

    fun setUserDetail(username: String){
        ApiConf.apiInstance
            .getUserDetail(username)
            .enqueue(object: Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Gagal", t.message.toString())
                }

            })
    }

    fun getUserDetail():LiveData<DetailUserResponse>{
        return user
    }

    fun addFavorite(username: String, id: Int, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = UserFavorite(
                username,
                id,
                avatarUrl
            )
            userDao?.addFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFavorite(id)
        }
    }
}