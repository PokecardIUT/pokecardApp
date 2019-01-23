package com.example.lpiem.pokecardapp.presentation.navigator

import androidx.fragment.app.FragmentManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.ui.fragment.AccountFragment
import com.example.lpiem.pokecardapp.presentation.ui.fragment.CardListFragment
import com.example.lpiem.pokecardapp.presentation.ui.fragment.RankingFragment
import com.example.lpiem.pokecardapp.presentation.ui.fragment.SetsListFragment

class Navigator(fragmentManager: FragmentManager){

    private val fragmentManager: FragmentManager = fragmentManager

    fun displaySetsList(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, SetsListFragment.newInstance()).commit()
    }

    fun displayCardList(setCode: String){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, CardListFragment.newInstance(setCode)).commit()

    }

    fun displayAccount(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, AccountFragment.newInstance()).commit()
    }

    fun displayRanking(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, RankingFragment.newInstance()).commit()
    }
}
