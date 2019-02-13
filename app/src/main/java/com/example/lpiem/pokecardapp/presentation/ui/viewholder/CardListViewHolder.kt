package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R


import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.squareup.picasso.Picasso

class CardListViewHolder(var v: View): RecyclerView.ViewHolder(v) {

    private val cardImageView: ImageView = itemView.findViewById(R.id.adapter_card_list_card)


    fun bindSet(card: Card, onSetClick: ((Card) -> Unit)?) {
        cardImageView.setImageResource(R.mipmap.card_hide)
        Picasso.get().load(card.imageUrl).placeholder(R.mipmap.card_hide).into(cardImageView)
    }

}