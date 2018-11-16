package com.example.lpiem.pokecardapp.data.repository


import com.example.lpiem.pokecardapp.data.manager.api.PokemonTCGApiImpl
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import rx.Observable


class PokeCardRepo{
    var pokemonTCGApi= PokemonTCGApiImpl();

    fun getDecks(): Observable<List<SetsItem?>> {
        return pokemonTCGApi.getDecks()
    }
}