package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.User.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class RankingViewModel: ViewModel() {

    var repo = PokeApplication.getInstance().repository
    var userList = MutableLiveData<List<User>>()
    var users = MutableLiveData<List<User>>()

    fun getUsers(){
        repo.getUsers().enqueue(object : Callback<List<User>> {

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("mlk", response.body().toString())

                users.postValue(response.body())

                val list: ArrayList<User> = users.value as ArrayList<User>
                list.sortWith(Comparator { user1, user2 -> user1.level!!.compareTo(user2.level!!) })
                list.reverse()
                userList.postValue(list)
            }


        })
    }

    fun getUserList() : LiveData<List<User>> = userList

}