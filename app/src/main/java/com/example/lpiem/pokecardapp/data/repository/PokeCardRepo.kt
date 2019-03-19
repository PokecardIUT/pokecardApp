package com.example.lpiem.pokecardapp.data.repository


import android.content.Context
import android.content.SharedPreferences
import com.example.lpiem.pokecardapp.data.manager.api.FacebookApiImpl
import com.example.lpiem.pokecardapp.data.manager.api.PokeCardApiImpl
import com.example.lpiem.pokecardapp.data.manager.sharedpreferences.SharedPreferencesManager
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import com.example.lpiem.pokecardapp.data.model.User.User
import retrofit2.Call

class PokeCardRepo{
    var pokeCardApi = PokeCardApiImpl()
    var facebookApi = FacebookApiImpl()
    var sharedPreferencesManager = SharedPreferencesManager()
    private lateinit var user: User

    fun connexionWithEmail(username:String, password:String): Call<Login>{
        return pokeCardApi.connexionWithEmail(username,password)
    }

    fun updateSharedPreferences(context: Context, user: User) {
        sharedPreferencesManager.updateSharedPreferences(context, user)
    }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        return sharedPreferencesManager.getSharedPreferences(context)
    }

    fun getSets(): Call<Deck>{
        return pokeCardApi.getSets(user.token!!)
    }

    fun getCardBySets(setCode: String): Call<SetCard>{
        return pokeCardApi.getCardBySets(setCode,user.token!!)
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User = user

    fun isLoggedFb(): Boolean = facebookApi.isLogged()

}