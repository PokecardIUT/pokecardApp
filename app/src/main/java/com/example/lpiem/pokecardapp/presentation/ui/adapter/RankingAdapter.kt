package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.RankingViewHolder
import com.example.lpiem.pokecardapp.presentation.utils.inflate

class RankingAdapter: RecyclerView.Adapter<RankingViewHolder>() {

    private val userList: MutableList<User> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val inflatedView = parent.inflate(R.layout.adapter_user_list, false)
        return RankingViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val userItem = userList[position]

        holder.bindUser(userItem, position)
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