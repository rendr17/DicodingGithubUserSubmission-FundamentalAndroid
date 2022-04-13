package com.rend.bangkit2022.githubuserapp2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rend.bangkit2022.githubuserapp2.api.ApiConf
import com.rend.bangkit2022.githubuserapp2.data.model.User
import com.rend.bangkit2022.githubuserapp2.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel :ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query: String){
        ApiConf.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Gagal", t.message.toString())
                }
            })
    }

    fun getSearchUser(): LiveData<ArrayList<User>>{
        return listUsers
    }
}