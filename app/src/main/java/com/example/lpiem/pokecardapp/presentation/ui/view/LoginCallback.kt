package com.example.lpiem.pokecardapp.presentation.ui.view

import android.content.Context

interface LoginCallback {
    fun showError(message: String)
    fun goToPokeCardActivity()
    fun getContext(): Context
}