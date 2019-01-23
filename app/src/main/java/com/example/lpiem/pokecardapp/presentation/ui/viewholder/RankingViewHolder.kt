package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.User

class RankingViewHolder(var v: View): RecyclerView.ViewHolder(v) {

    private val userImageView: ImageView = itemView.findViewById(R.id.adapter_user_list_imageview)
    private val userName: TextView = itemView.findViewById(R.id.adapter_user_list_name)
    private val userPosition: TextView = itemView.findViewById(R.id.adapter_user_list_position)
    private val userLevel: TextView = itemView.findViewById(R.id.adapter_user_list_level)
    private val userNbWin: TextView = itemView.findViewById(R.id.adapter_user_list_number_win)

    fun bindUser(user: User, position: Int){

        userName.text = user.name
        userPosition.text = (position+1).toString()
        userLevel.text = "LV ${user.level.toString()}"
        userNbWin.text = "${user.nbWin.toString()} victoires"
        when(position+1){
            1 -> userImageView.setImageResource(R.drawable.ic_gold_medal)
            2 -> userImageView.setImageResource(R.drawable.ic_silver_icon)
            3 -> userImageView.setImageResource(R.drawable.ic_bronze_medal)
        }
    }

}