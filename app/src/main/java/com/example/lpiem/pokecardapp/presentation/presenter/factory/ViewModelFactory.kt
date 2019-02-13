package com.example.lpiem.pokecardapp.presentation.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lpiem.pokecardapp.presentation.presenter.CardListViewModel
import com.example.lpiem.pokecardapp.presentation.presenter.LoginViewModel
import com.example.lpiem.pokecardapp.presentation.presenter.RankingViewModel
import com.example.lpiem.pokecardapp.presentation.presenter.SetsListViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        }
        if(modelClass.isAssignableFrom(SetsListViewModel::class.java)){
            return SetsListViewModel() as T
        }
        if(modelClass.isAssignableFrom(CardListViewModel::class.java)){
            return CardListViewModel() as T
        }
        if(modelClass.isAssignableFrom(RankingViewModel::class.java)){
            return RankingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}