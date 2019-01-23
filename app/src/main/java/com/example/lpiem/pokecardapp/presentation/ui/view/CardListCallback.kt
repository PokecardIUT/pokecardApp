package com.example.lpiem.pokecardapp.presentation.ui.view

import com.example.lpiem.pokecardapp.data.model.SetCard.Card


interface CardListCallback {
    fun updateList(setsList: List<Card>)
}