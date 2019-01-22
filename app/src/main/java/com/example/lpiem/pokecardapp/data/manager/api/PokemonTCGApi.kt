package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.data.model.Pokemon.Pokemon
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonTCGApi {
    @GET("/v1/sets")
    fun getSets(): Call<Deck>
}