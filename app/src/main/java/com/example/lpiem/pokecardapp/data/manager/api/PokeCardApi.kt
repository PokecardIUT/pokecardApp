package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import retrofit2.Call
import retrofit2.http.*

interface PokeCardApi {
    @POST("/login/email")
    @FormUrlEncoded
    fun connexionWithEmail(@Field("username") username: String,
                           @Field("password") password: String): Call<Login>

   
    @GET("/api/decks")
    fun getSets(@Query("access_token") accessToken: String): Call<Deck>

    @GET("/api/cards/{setCode}/all")
    fun getCardBySets(@Path("setCode") setCode: String, @Query("access_token") accessToken: String): Call<SetCard>

    @GET("/api/randomCard")
    fun getRandomCard(@Query("username") username: String,
                      @Query("id") id: String,
                      @Query("pageSize") pageSize: String,
                      @Query("page") page: String,
                      @Query("nbCard") nbCard: String,
                      @Query("access_token") accessToken: String): Call<List<Card>>
}