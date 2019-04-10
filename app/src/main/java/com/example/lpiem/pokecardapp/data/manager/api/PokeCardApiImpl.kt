package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.BuildConfig
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.data.model.Login.ResultCode
import com.example.lpiem.pokecardapp.data.model.SuccessMessage
import com.example.lpiem.pokecardapp.data.model.TradeData
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.data.model.User.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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

    override fun getUsers(accessToken: String): Call<List<User>> {
        return pokeCardApi.getUsers(accessToken)
    }
    
    override fun signup(username: String, password: String): Call<ResultCode> {
        return pokeCardApi.signup(username,password)
    }
    
    override fun connexionWithService(username: String, secret: String): Call<Login> {
        return pokeCardApi.connexionWithService(username, secret)
    }

    override fun getSets(accessToken: String): Call<Deck> {
        return pokeCardApi.getSets(accessToken)
    }

    override fun getCardBySets(setCode: String, accessToken: String): Call<SetCard> {
       return pokeCardApi.getCardBySets(setCode,accessToken)
    }

    override fun getRandomCard(username: String, id: String, pageSize: String, page: String, nbCard: String, accessToken: String): Call<List<Card>> {
        return pokeCardApi.getRandomCard(username, id, pageSize, page, nbCard, accessToken)
    }

    override fun getCardsCount(username: String, id: String, pageSize: String, page: String, accessToken: String): Call<CardsCount> {
        return pokeCardApi.getCardsCount(username, id, pageSize, page, accessToken)
    }

    override fun getUser(accessToken: String, username: String): Call<UserResponse> {
        return pokeCardApi.getUser(accessToken,username)
    }

    override fun trade(accessToken: String, users: List<User>, cards: List<Card>): Call<SuccessMessage<List<TradeData>>> {
        return pokeCardApi.trade(accessToken, users, cards)
    }
    
    override fun addSet(username: String, set: String, accessToken: String): Call<UserResponse> {
       return pokeCardApi.addSet(username, set,accessToken)
    }
}