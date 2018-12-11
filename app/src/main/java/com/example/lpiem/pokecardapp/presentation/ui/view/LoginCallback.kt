package com.example.lpiem.pokecardapp.presentation.ui.view

import android.content.Context

interface LoginCallback {
    fun showError(message: String)
    fun goToDeckListActivity(method:String, name:String, email:String)
    fun getContext(): Context
}