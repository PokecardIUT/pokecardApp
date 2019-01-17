package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.adapter.DeckListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.view.DeckListCallback
import com.example.lpiem.pokecardapp.presentation.viewModel.DeckListViewModel
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_deck_list.*
import kotlin.system.exitProcess

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
