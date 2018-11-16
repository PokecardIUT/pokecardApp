package com.example.lpiem.pokecardapp.presentation.ui.view

import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem

interface DeckListCallback {

    fun notifyDataChange(listDeck: List<SetsItem?>)
}