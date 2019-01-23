package com.example.lpiem.pokecardapp.presentation.ui.view

import com.example.lpiem.pokecardapp.data.model.Deck.User

interface RankingCallback {

    fun updateList(setsList: List<User>)
}