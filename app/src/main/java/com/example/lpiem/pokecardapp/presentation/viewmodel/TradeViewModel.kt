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

    fun isTradeActive() : LiveData<Boolean> = isTrade
}