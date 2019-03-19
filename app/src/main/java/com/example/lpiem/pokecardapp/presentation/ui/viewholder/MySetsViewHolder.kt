package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.SetsUser

class MySetsViewHolder(var v: View): RecyclerView.ViewHolder(v) {

    private val setsTitle: TextView = itemView.findViewById(R.id.adapter_my_sets_title)
    private val setsDescription: TextView = itemView.findViewById(R.id.adapter_my_sets_description)
    private val setsNumberCard: TextView = itemView.findViewById(R.id.adapter_my_sets_nb_card)

    fun bindSet(set: SetsUser){

        setsTitle.text = set.title
        setsDescription.text = set.description
        setsNumberCard.text = set.cards?.count().toString()

    }

}