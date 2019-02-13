package com.example.lpiem.pokecardapp.data.manager.api

import com.example.lpiem.pokecardapp.data.model.Login.Login
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface PokeCardApi {
    @POST("/login/email")
    @FormUrlEncoded
    fun connexionWithEmail(@Field("username") username: String,
                           @Field("password") password: String): Call<Login>
}