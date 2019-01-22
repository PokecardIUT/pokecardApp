package com.example.lpiem.pokecardapp.presentation.presenter

import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback

class AccountPresenter {
    var repo = PokeApplication.getInstance().repository

    fun getName():String {
        return repo.username
    }

    fun getPassword():String {
        return repo.password
    }
    fun isLoggedFb(): Boolean = repo.isLoggedFb()

}