package com.example.lpiem.pokecardapp.data.repository


import com.example.lpiem.pokecardapp.data.manager.api.FacebookApiImpl
import com.example.lpiem.pokecardapp.data.manager.api.PokeCardApiImpl
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.example.lpiem.pokecardapp.data.model.Login.ResultCode
import com.example.lpiem.pokecardapp.data.model.SetCard.SetCard
import com.example.lpiem.pokecardapp.data.model.SuccessMessage
import com.example.lpiem.pokecardapp.data.model.Trade.Trade
import com.example.lpiem.pokecardapp.data.model.TradeData
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.data.model.User.UserResponse
import com.google.gson.Gson
import retrofit2.Call

class PokeCardRepo{
    var pokeCardApi = PokeCardApiImpl()
    var facebookApi = FacebookApiImpl()
    private lateinit var user: User

    fun connexionWithEmail(username:String, password:String): Call<Login>{
        return pokeCardApi.connexionWithEmail(username,password)
    }

    fun signup(username:String, password:String): Call<ResultCode> {
        return pokeCardApi.signup(username,password)
    }
    
    fun connexionWithService(): Call<Login>{
        return pokeCardApi.connexionWithService(this.user.username!!, "valid")
    }

    fun getSets(): Call<Deck>{
        return pokeCardApi.getSets(user.token!!)
    }

    fun getUsers(): Call<List<User>>{
        return pokeCardApi.getUsers(user.token!!)
    }

    fun getCardBySets(setCode: String): Call<SetCard>{
        return pokeCardApi.getCardBySets(setCode,user.token!!)
    }

    fun getRandomCard(id: String, nbCard: String): Call<List<Card>>{
        return pokeCardApi.getRandomCard(user.username!!, id, "1000", "1", nbCard, user.token!!)
    }

    fun getCardsCount(id: String): Call<CardsCount>{
        return pokeCardApi.getCardsCount(user.username!!, id, "1000", "1", user.token!!)
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User = user

    fun retrieveUser(): Call<UserResponse>{
        return pokeCardApi.getUser(this.user.token!!,this.user.username!!)
    }

    fun trade(users: List<String>, cards: List<String>): Call<Trade>{
        return pokeCardApi.trade(this.user.token!!, users, cards)
    }
    
    fun addSet(set: SetsUser): Call<UserResponse> {
        return pokeCardApi.addSet(this.user.username!!, Gson().toJson(set), this.user.token!!)
    }

    fun isLoggedFb(): Boolean = facebookApi.isLogged()

}