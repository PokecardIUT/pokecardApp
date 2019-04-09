package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.User

class TradeViewHolder(var v: View): RecyclerView.ViewHolder(v) {

    private val userName: TextView = itemView.findViewById(R.id.adapter_user_list_name)
    private val userLevel: TextView = itemView.findViewById(R.id.adapter_user_list_level)
    private val userNbWin: TextView = itemView.findViewById(R.id.adapter_user_list_number_win)

    fun bindUser(user: User){

        userName.text = user.username
        userLevel.text = "LV ${user.level.toString()}"
        userNbWin.text = "${user.nbWin.toString()} victoires"
    }

}