package com.example.lpiem.pokecardapp.presentation.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.adapter_deck_list.view.*

class DeckListViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvName: TextView =  view.tvName
    val tvSerie: TextView =  view.tvSerie
    val tvRelease: TextView =  view.tvRelease
    val tvTotal: TextView =  view.tvTotal
    val imageView: ImageView =  view.imageView

}