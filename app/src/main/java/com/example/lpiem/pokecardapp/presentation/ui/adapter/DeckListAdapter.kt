package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.DeckListViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_deck_list.view.*

class DeckListAdapter(var items : List<SetsItem?>, val context: Context) : RecyclerView.Adapter<DeckListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckListViewHolder {
        return DeckListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_deck_list, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DeckListViewHolder, position: Int) {
        holder.tvName.text = items.get(position)?.name
        holder.tvSerie.text = items.get(position)?.series
        holder.tvRelease.text = "Released on "+items.get(position)?.releaseDate
        holder.tvTotal.text = items.get(position)?.totalCards.toString()
        Picasso.get().load(items.get(position)?.logoUrl).into(holder.imageView);
    }

    override fun getItemCount(): Int {
        return items.size
    }

}


