package com.example.lpiem.pokecardapp.presentation.presenter

import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.ui.view.RankingCallback
import java.util.*

class RankingPresenter(var view: RankingCallback) {
    fun getRank(){

        val userList: ArrayList<User> = ArrayList()

        userList.add(User("Theo", 50, 250))
        userList.add(User("Loic", 150, 150))
        userList.add(User("Thomas", 10, 15))
        userList.add(User("Morgane", 5, 5))

        Collections.sort(userList) { user1, user2 -> user1.level!!.compareTo(user2.level!!) }
        Collections.reverse(userList)
        view.updateList(userList)
    }


}