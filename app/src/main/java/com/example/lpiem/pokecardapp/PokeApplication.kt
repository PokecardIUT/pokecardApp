package com.example.lpiem.pokecardapp

import android.app.Application
import com.example.lpiem.pokecardapp.data.repository.PokeCardRepo

class PokeApplication: Application() {
    companion object {
        var app = this
        var repository = PokeCardRepo()
    }
}