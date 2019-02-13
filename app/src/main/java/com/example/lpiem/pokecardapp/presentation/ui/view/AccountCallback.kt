package com.example.lpiem.pokecardapp.presentation.ui.view

import com.example.lpiem.pokecardapp.data.model.User.User

interface AccountCallback {
    fun showUserAccount(user: User)
}