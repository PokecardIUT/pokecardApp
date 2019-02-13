package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.BuildConfig
import com.example.lpiem.pokecardapp.data.model.Login.Login
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokeCardApiImpl : PokeCardApi {

    var pokeCardApi: PokeCardApi

    init {
        val hli = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            hli.level = HttpLoggingInterceptor.Level.BODY
        } else {
            hli.level = HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(hli)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokecardduel.herokuapp.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()

        pokeCardApi = retrofit.create(PokeCardApi::class.java)
    }

    override fun connexionWithEmail(username: String, password: String): Call<Login> {
        return pokeCardApi.connexionWithEmail(username, password)
    }
}