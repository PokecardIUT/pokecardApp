package com.example.lpiem.pokecardapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.Login.ResultCode
import com.example.lpiem.pokecardapp.data.model.User.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel: ViewModel() {

    var error = MutableLiveData<ErrorMessage>()
    var repository = PokeApplication.getInstance().repository
    var successLiveData = MutableLiveData<ResultCode>()

    fun getError(): LiveData<ErrorMessage> = error
    fun getSuccess(): LiveData<ResultCode> = successLiveData

    fun signup(email: String, password: String, confirmPassword: String){
        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            error.postValue(ErrorMessage("Un champs n'a pas été remplis"))
            return
        }

        if(password != confirmPassword) {
            error.postValue(ErrorMessage("Les mot de passe ne sont pas identique"))
            return
        }

        repository.signup(email, password).enqueue(object : Callback<ResultCode> {
            override fun onFailure(call: Call<ResultCode>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResultCode>, response: Response<ResultCode>) {
                if(response.body()?.code != 200){
                    error.postValue(ErrorMessage(response.body()?.message))
                } else {
                    successLiveData.postValue(response.body())
                }
            }


        })

    }
}