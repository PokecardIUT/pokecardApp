package com.example.lpiem.pokecardapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator

class SearchCardActivity : AppCompatActivity() {

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_card)

        navigator = Navigator(supportFragmentManager)

        if(savedInstanceState == null){
            navigator.displaySetsList()
        }

    }
}