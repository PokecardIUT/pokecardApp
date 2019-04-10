package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.example.lpiem.pokecardapp.data.model.SuccessMessage
import com.example.lpiem.pokecardapp.data.model.Trade.Trade
import com.example.lpiem.pokecardapp.data.model.TradeData
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.data.model.User.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeViewModel: ViewModel() {
    var repo = PokeApplication.getInstance().repository
    var isTrade = MutableLiveData<Boolean>()
    var userList = MutableLiveData<List<User>>()
    var userLiveData = MutableLiveData<User>()
    var error = MutableLiveData<ErrorMessage>()

    fun trade(users: List<String>, cards: List<String>) {
        repo.trade(users, cards).enqueue(object : Callback<Trade> {
            override fun onFailure(call: Call<Trade>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<Trade>, response: Response<Trade>) {
                Log.d("mlk", response.body().toString())

                isTrade.postValue(true)
            }

        })
    }


    fun getUsers() {
        repo.getUsers().enqueue(object : Callback<List<User>> {

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("mlk", response.body().toString())

                val list: ArrayList<User> = ArrayList(response.body())
                list.sortWith(Comparator { user1, user2 -> user1.level!!.compareTo(user2.level!!) })
                list.reverse()
                userList.postValue(list)
            }
        })
    }

    fun retrieveUser() {
        repo.retrieveUser().enqueue(object : Callback<UserResponse> {
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

    fun getUserList() : LiveData<List<User>> = userList

    fun getError(): LiveData<ErrorMessage> = error

    fun isTradeActive() : LiveData<Boolean> = isTrade
}