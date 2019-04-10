package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.example.lpiem.pokecardapp.data.model.SuccessMessage
import com.example.lpiem.pokecardapp.data.model.TradeData
import com.example.lpiem.pokecardapp.data.model.User.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeViewModel: ViewModel() {
    var repo = PokeApplication.getInstance().repository
    var isTrade = MutableLiveData<Boolean>()
    var userList = MutableLiveData<List<User>>()

    fun trade(users: List<User>, cards: List<Card>) {
        repo.trade(users, cards).enqueue(object : Callback<SuccessMessage<List<TradeData>>> {
            override fun onFailure(call: Call<SuccessMessage<List<TradeData>>>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<SuccessMessage<List<TradeData>>>, response: Response<SuccessMessage<List<TradeData>>>) {
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

    fun getUserList() : LiveData<List<User>> = userList

    fun isTradeActive() : LiveData<Boolean> = isTrade
}