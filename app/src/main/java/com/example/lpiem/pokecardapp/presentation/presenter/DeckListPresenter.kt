package com.example.lpiem.pokecardapp.presentation.presenter

import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.view.DeckListCallback


class DeckListPresenter(var view:DeckListCallback) {
    var repo = PokeApplication.app.repository
    var listDeck: List<SetsItem?> = ArrayList<SetsItem?>()
    fun getDecks(){
        repo.getDecks().subscribe {
            Log.d("mlk", "sub")
            listDeck = it
            view.notifyDataChange(it)
        }

    }

    fun isLoggedFb(): Boolean = repo.isLoggedFb()
}