package com.example.lpiem.pokecardapp.presentation.navigator

import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.ui.fragment.*

class Navigator(fragmentManager: FragmentManager){

    private val fragmentManager: FragmentManager = fragmentManager

    fun displaySetsList(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, SetsListFragment.newInstance()).commit()
    }

    fun displaySetsListShop(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, SetsListFragment.newInstance("shop")).addToBackStack(null).commit()
    }

    fun displayCardList(setCode: String){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, CardListFragment.newInstance(setCode)).addToBackStack(null).commit()

    }

    fun displayShop(id: String){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, ShopFragment.newInstance(id)).addToBackStack(null).commit()

    }

    fun displayAccount(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, AccountFragment.newInstance()).commit()
    }

    fun displayRanking(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, RankingFragment.newInstance()).commit()
    }

    fun displayMySets(){
        fragmentManager.beginTransaction().replace(R.id.search_card_framelayout, MySetsFragment.newInstance()).commit()
    }
}
