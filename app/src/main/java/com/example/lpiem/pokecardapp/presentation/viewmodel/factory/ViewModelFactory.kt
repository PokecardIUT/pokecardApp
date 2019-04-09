package com.example.lpiem.pokecardapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lpiem.pokecardapp.presentation.viewmodel.*

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        }
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel() as T
        }
        if(modelClass.isAssignableFrom(SetsListViewModel::class.java)){
            return SetsListViewModel() as T
        }
        if(modelClass.isAssignableFrom(CardListViewModel::class.java)){
            return CardListViewModel() as T
        }
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel() as T
        }
        if(modelClass.isAssignableFrom(RankingViewModel::class.java)){
            return RankingViewModel() as T
        }
        if(modelClass.isAssignableFrom(ShopViewModel::class.java)){
            return ShopViewModel() as T
        }
        if(modelClass.isAssignableFrom(MySetsViewModel::class.java)){
            return MySetsViewModel() as T
        }
        if(modelClass.isAssignableFrom(TradeViewModel::class.java)){
            return TradeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}