package com.example.lpiem.pokecardapp.presentation.presenter

import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.view.DeckListCallback
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback

class SetsListPresenter(var view: SetsListCallback) {
    var repo = PokeApplication.app.repository
    var listDeck: List<SetsItem?> = ArrayList<SetsItem?>()
    fun getDecks(){
        repo.getDecks().subscribe {
            listDeck = it
            view.updateList(it)
        }

    }

    fun isLoggedFb(): Boolean = repo.isLoggedFb()

}