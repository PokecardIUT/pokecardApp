package com.example.lpiem.pokecardapp

import android.app.Application
import com.example.lpiem.pokecardapp.data.repository.PokeCardRepo

class PokeApplication: Application() {


    lateinit var repository: PokeCardRepo

    override fun onCreate() {
        super.onCreate()
        app = this
        repository = PokeCardRepo()
    }



    companion object {
        private var app: PokeApplication = PokeApplication()

        fun getInstance():PokeApplication = app
}
}