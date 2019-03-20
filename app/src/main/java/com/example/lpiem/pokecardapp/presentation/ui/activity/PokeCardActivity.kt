package com.example.lpiem.pokecardapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_poke_card.*

class PokeCardActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_card)

        title = "Rechercher"

        bottom_navigation.setOnNavigationItemSelectedListener(this)

        navigator = Navigator(supportFragmentManager)

        if(savedInstanceState == null){
            navigator.displaySetsList()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        when (item.itemId) {
            R.id.bottom_navigation_search -> {
                navigator.displaySetsList()
                title = getString(R.string.bottom_navigation_search)
            }
            R.id.bottom_navigation_account -> {
                navigator.displayAccount()
                title = getString(R.string.bottom_navigation_account)
            }

            R.id.bottom_navigation_ranking -> {
                navigator.displayRanking()
                title = getString(R.string.bottom_navigation_ranking)
            }

            R.id.bottom_navigation_my_sets -> {
                Log.d("mlk","coucou")
                navigator.displayMySets()
                title = "Mes decks"
            }

        }
        return false
    }
}
