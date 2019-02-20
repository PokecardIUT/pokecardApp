package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardListViewModel: ViewModel() {
    var repo = PokeApplication.getInstance().repository
    var cardList = MutableLiveData<List<Card>>()

    fun getCardBySets(setCode: String) {
        repo.getCardBySets(setCode).enqueue(object : Callback<SetCard> {
            override fun onFailure(call: Call<SetCard>, t: Throwable) {
                Log.d("mlk","failure")
            }

            override fun onResponse(call: Call<SetCard>, response: Response<SetCard>) {
                Log.d("mlk","succes")

                cardList.postValue(response.body()?.cards!!)
            }


        })
    }

    fun getCardList() : LiveData<List<Card>> = cardList

}