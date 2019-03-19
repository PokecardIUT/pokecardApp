package com.example.lpiem.pokecardapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.data.model.User.User
import java.util.*

class RankingViewModel: ViewModel() {
    var userList = MutableLiveData<List<User>>()

    fun getRank(){

        val list: ArrayList<User> = ArrayList()

        Collections.sort(list) { user1, user2 -> user1.level!!.compareTo(user2.level!!) }
        Collections.reverse(list)
        userList.postValue(list)
    }

    fun getUserList() : LiveData<List<User>> = userList

}