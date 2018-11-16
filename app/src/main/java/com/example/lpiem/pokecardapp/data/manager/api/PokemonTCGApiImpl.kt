package com.example.lpiem.pokecardapp.data.manager.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonTCGApiImpl {
    private val pokemonTCGClient: PokemonTCGApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        pokemonTCGClient = retrofit.create(PokemonTCGApi::class.java)
    }

    fun getRxCardName(id: String): Observable<String> {
        return pokemonTCGClient.getCard(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { pokemon -> "Card: " + pokemon.card?.name }
    }
}
