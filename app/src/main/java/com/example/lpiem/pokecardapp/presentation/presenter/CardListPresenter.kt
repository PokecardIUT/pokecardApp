package com.example.lpiem.pokecardapp.presentation.presenter

import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import com.example.lpiem.pokecardapp.presentation.ui.view.CardListCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardListPresenter(var view: CardListCallback) {
    var repo = PokeApplication.getInstance().repository

    fun getCardBySets(setCode: String) {
        repo.getCardBySets(setCode).enqueue(object : Callback<SetCard> {
            override fun onFailure(call: Call<SetCard>, t: Throwable) {
                Log.d("mlk","failure")
            }

            override fun onResponse(call: Call<SetCard>, response: Response<SetCard>) {
                Log.d("mlk","succes")

                view.updateList(response.body()?.cards!!)
            }


        })
    }

}