package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.TradeViewHolder
import com.example.lpiem.pokecardapp.presentation.utils.inflate

class TradeAdapter: RecyclerView.Adapter<TradeViewHolder>() {

    private val userList: MutableList<User> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeViewHolder {
        val inflatedView = parent.inflate(R.layout.adapter_trade, false)
        return TradeViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: TradeViewHolder, position: Int) {
        val userItem = userList[position]

        holder.bindUser(userItem)
    }

    fun addUserList(userList : List<User>){
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    fun updateUserList(userList: List<User>){
        this.userList.clear()
        addUserList(userList)
    }
}