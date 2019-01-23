package com.example.lpiem.pokecardapp.data.repository


import com.example.lpiem.pokecardapp.data.manager.api.FacebookApiImpl
import com.example.lpiem.pokecardapp.data.manager.api.PokeCardApiImpl
import com.example.lpiem.pokecardapp.data.manager.api.PokemonTCGApiImpl
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import retrofit2.Call
import rx.Observable


class PokeCardRepo{
    var pokemonTCGApi= PokemonTCGApiImpl()
    var pokeCardApi = PokeCardApiImpl()
    var facebookApi = FacebookApiImpl()
    lateinit var username: String

    fun connexionWithEmail(username:String, password:String): Observable<Login>{
        this.username = username
        return pokeCardApi.connexionWithEmail(username,password)
    }


    fun getSets(): Call<Deck>{
        return pokemonTCGApi.getSets()
    }

    fun isLoggedFb(): Boolean = facebookApi.isLogged()

}