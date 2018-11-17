package com.example.lpiem.pokecardapp.presentation.ui.view

interface LoginCallback {
    fun showError(message: String)
    fun goToDeckListActivity()
}