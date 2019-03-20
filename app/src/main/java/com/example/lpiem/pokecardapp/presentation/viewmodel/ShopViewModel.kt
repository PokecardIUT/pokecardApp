package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopViewModel: ViewModel() {
    var repo = PokeApplication.getInstance().repository
    var cardList = MutableLiveData<List<Card>>()
    var cardsCount= MutableLiveData<CardsCount>()

    fun getRandomCard(id: String, nbCard: String) {
        repo.getRandomCard(id, nbCard).enqueue(object : Callback<List<Card>> {
            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                Log.d("mlk", response.body().toString())

                cardList.postValue(response.body())
            }


        })
    }

    fun getCardsCount(id: String) {
        repo.getCardsCount(id).enqueue(object : Callback<CardsCount> {
            override fun onFailure(call: Call<CardsCount>, t: Throwable) {
                Log.d("mlk", "failure")
            }

            override fun onResponse(call: Call<CardsCount>, response: Response<CardsCount>) {
                Log.d("mlk", response.body().toString())

                cardsCount.postValue(response.body())
            }


        })
    }

    fun getCardList() : LiveData<List<Card>> = cardList
    fun getCardsCount() : LiveData<CardsCount> = cardsCount
}