package com.example.lpiem.pokecardapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SetsListViewModel: ViewModel() {

    var repo = PokeApplication.getInstance().repository
    var listSets = MutableLiveData<List<SetsItem>>()

    fun getSets(){

        repo.getSets().enqueue(object : Callback<Deck>{
            override fun onFailure(call: Call<Deck>, t: Throwable) {

            }

            override fun onResponse(call: Call<Deck>, response: Response<Deck>) {
                listSets.postValue(response.body()?.sets!!)
            }


        })
    }

    fun getListSets(): LiveData<List<SetsItem>> = listSets

}