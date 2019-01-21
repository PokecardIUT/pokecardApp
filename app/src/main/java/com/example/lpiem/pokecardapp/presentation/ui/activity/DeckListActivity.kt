package com.example.lpiem.pokecardapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.lpiem.pokecardapp.R

class DeckListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deck_list)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    //    finishAffinity()
    }
}
