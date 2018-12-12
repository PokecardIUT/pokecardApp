package com.example.lpiem.pokecardapp.data.repository


import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.lpiem.pokecardapp.data.manager.api.PokeCardApiImpl
import com.example.lpiem.pokecardapp.data.manager.api.PokemonTCGApiImpl
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.data.model.Login.Login
import rx.Observable


class PokeCardRepo{
    var pokemonTCGApi= PokemonTCGApiImpl()
    var pokeCardApi = PokeCardApiImpl()

    fun getDecks(): Observable<List<SetsItem?>> {
        return pokemonTCGApi.getDecks()
    }

    fun connexionWithEmail(username:String, password:String): Observable<Login>{
        return pokeCardApi.connexionWithEmail(username,password)
    }
}