package com.example.lpiem.pokecardapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.User.User

class AccountViewModel: ViewModel() {
    var repo = PokeApplication.getInstance().repository
    var user = MutableLiveData<User>()
    var isLoggededFbLiveData = MutableLiveData<Boolean>()

    fun retrieveUser() {
        user.postValue(repo.getUser())
    }

    fun isLoggedFb(): Boolean = repo.isLoggedFb()

    fun getUser(): LiveData<User> = user
}