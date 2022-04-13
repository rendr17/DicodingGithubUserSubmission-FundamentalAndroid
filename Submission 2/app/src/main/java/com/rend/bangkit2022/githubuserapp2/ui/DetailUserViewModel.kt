package com.rend.bangkit2022.githubuserapp2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rend.bangkit2022.githubuserapp2.api.ApiConf
import com.rend.bangkit2022.githubuserapp2.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {

    val user = MutableLiveData<DetailUserResponse>()

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
}