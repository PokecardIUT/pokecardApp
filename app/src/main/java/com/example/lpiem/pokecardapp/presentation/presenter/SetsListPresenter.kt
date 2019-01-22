package com.example.lpiem.pokecardapp.presentation.presenter

import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetsListPresenter(var view: SetsListCallback) {
    var repo = PokeApplication.getInstance().repository

    fun getSets(){

        repo.getSets().enqueue(object : Callback<Deck>{
            override fun onFailure(call: Call<Deck>, t: Throwable) {

            }

            override fun onResponse(call: Call<Deck>, response: Response<Deck>) {
                view.updateList(response.body()?.sets!!)
            }


        })
    }


}