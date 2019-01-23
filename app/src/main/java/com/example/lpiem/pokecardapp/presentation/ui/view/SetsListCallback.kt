package com.example.lpiem.pokecardapp.presentation.ui.view

import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem

interface SetsListCallback {

    fun updateList(setsList: List<SetsItem>)
}