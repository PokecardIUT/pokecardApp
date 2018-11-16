package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.Deck
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_deck_list.view.*


class DeckListAdapter(val items : List<SetsItem?>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_deck_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = items.get(position)?.name
        holder.tvSerie.text = items.get(position)?.series
        holder.tvRelease.text = "Released on "+items.get(position)?.releaseDate
        holder.tvTotal.text = items.get(position)?.totalCards.toString()
        Picasso.get().load(items.get(position)?.logoUrl).into(holder.imageView);
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

}
class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName =  view.tvName
    val tvSerie =  view.tvSerie
    val tvRelease =  view.tvRelease
    val tvTotal =  view.tvTotal
    val imageView =  view.imageView
}