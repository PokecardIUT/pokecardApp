package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTCGApi {
    @GET("/v1/sets")
    fun getSets(): Call<Deck>
}