package com.example.lpiem.pokecardapp.presentation.navigator

import androidx.fragment.app.FragmentManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.ui.fragment.SetsListFragment

class Navigator(fragmentManager: FragmentManager){

    private val fragmentManager: FragmentManager = fragmentManager

    fun displaySetsList(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, SetsListFragment.newInstance()).commit()
    }
}
