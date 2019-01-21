package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.squareup.picasso.Picasso

class SetsListViewHolder(var v: View): RecyclerView.ViewHolder(v) {

    private val setsImageView: ImageView = itemView.findViewById(R.id.adapter_sets_list_imageview)
    private val setsTitle: TextView = itemView.findViewById(R.id.adapter_sets_list_title)
    private val setsDescription: TextView = itemView.findViewById(R.id.adapter_sets_list_description)
    private val setsReleaseDate: TextView = itemView.findViewById(R.id.adapter_sets_list_release_date)
    private val setsNumberCard: TextView = itemView.findViewById(R.id.adapter_sets_list_number_card)

    fun bindSet(set: SetsItem, onSetClick: ((SetsItem)->Unit)?){

        setsTitle.text = set.name
        setsDescription.text = set.series
        setsReleaseDate.text = set.releaseDate
        setsNumberCard.text = set.totalCards.toString()
        Picasso.get().load(set.logoUrl).into(setsImageView)
        itemView.setOnClickListener{
            onSetClick?.let {
                it(set)
            }
        }

    }

}