package com.example.lpiem.pokecardapp.data.manager.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonTCGService {
    private val pokemonTCGClient: PokemonTCGClient

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        pokemonTCGClient = retrofit.create(PokemonTCGClient::class.java!!)
    }

    fun getRxCardName(id: String): Observable<String> {
        return pokemonTCGClient.getCard(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { card -> "Card: " + card.name }
    }
}
