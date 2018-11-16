package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Card

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonTCGClient {
    @GET("/v1/cards/{id}")
    fun getCard(@Path("id") card: String): Observable<Card>
}