package com.example.lpiem.pokecardapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.data.model.User.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateSetViewModel: ViewModel() {
    var repository = PokeApplication.getInstance().repository
    var userLiveData = MutableLiveData<User>()
    var error = MutableLiveData<ErrorMessage>()

    fun retrieveUser() {
        repository.retrieveUser().enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.body()?.code == 200){
                    userLiveData.postValue(response.body()?.user)
                } else {
                    error.postValue(ErrorMessage(response.body()?.message))
                }
            }


        })
    }

    fun getUser(): LiveData<User> = userLiveData

    fun getError(): LiveData<ErrorMessage> = error

}