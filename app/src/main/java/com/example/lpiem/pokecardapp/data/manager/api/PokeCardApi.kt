package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.data.model.Login.ResultCode
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.data.model.User.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface PokeCardApi {
    @POST("/login/email")
    @FormUrlEncoded
    fun connexionWithEmail(@Field("username") username: String,
                           @Field("password") password: String): Call<Login>

    @POST("/signup")
    @FormUrlEncoded
    fun signup(@Field("username") username: String,
               @Field("password") password: String): Call<ResultCode>
   
    @POST("/login/service")
    @FormUrlEncoded
    fun connexionWithService(@Field("username") username: String,
                             @Field("secret") secret: String): Call<Login>

    @GET("/api/decks")
    fun getSets(@Query("access_token") accessToken: String): Call<Deck>

    @GET("/api/users")
    fun getUsers(@Query("access_token") accessToken: String): Call<List<User>>

    @GET("/api/cardsCount")
    fun getCardsCount(@Query("username") username: String,
                      @Query("id") id: String,
                      @Query("pageSize") pageSize: String,
                      @Query("page") page: String,
                      @Query("access_token") accessToken: String): Call<CardsCount>

    @GET("/api/cards/{setCode}/all")
    fun getCardBySets(@Path("setCode") setCode: String, @Query("access_token") accessToken: String): Call<SetCard>

    @GET("/api/randomCard")
    fun getRandomCard(@Query("username") username: String,
                      @Query("id") id: String,
                      @Query("pageSize") pageSize: String,
                      @Query("page") page: String,
                      @Query("nbCard") nbCard: String,
                      @Query("access_token") accessToken: String): Call<List<Card>>

    @GET("/api/user")
    fun getUser(@Query("access_token") accessToken: String, @Query("username") username: String): Call<UserResponse>

    @POST("/api/setUpdate")
    @FormUrlEncoded
    fun addSet(@Field("username") username: String,
               @Field("set") set: SetsUser,
               @Query("access_token") accessToken: String): Call<UserResponse>

}