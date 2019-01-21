package com.example.lpiem.pokecardapp.data.manager.api

import android.util.Log
import com.example.lpiem.pokecardapp.BuildConfig
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonTCGApiImpl {
    private val pokemonTCGClient: PokemonTCGApi




    init {
        val hli = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            hli.level = HttpLoggingInterceptor.Level.BODY
        } else {
            hli.level = HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(hli)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()

        pokemonTCGClient = retrofit.create(PokemonTCGApi::class.java)
    }


    fun getDecks(): Observable<List<SetsItem>> {
        return pokemonTCGClient.getDecks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { listDeck -> listDeck.sets}
    }


}
