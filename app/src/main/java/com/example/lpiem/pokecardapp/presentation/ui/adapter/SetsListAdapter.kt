package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.SetsListViewHolder
import com.example.lpiem.pokecardapp.presentation.utils.inflate

class SetsListAdapter: RecyclerView.Adapter<SetsListViewHolder>() {

    private val setsList: MutableList<SetsItem> = mutableListOf()
    private  var onSetClickListener: ((SetsItem)->Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsListViewHolder {
        val inflatedView = parent.inflate(R.layout.adapter_sets_list, false)
        return SetsListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = setsList.size

    override fun onBindViewHolder(holder: SetsListViewHolder, position: Int) {
        val itemSet = setsList[position]

        holder.bindSet(itemSet,onSetClickListener)
    }

    fun addSetList(setsList : List<SetsItem>){
        this.setsList.addAll(setsList)
        notifyDataSetChanged()
    }

    fun updateSetList(setList: List<SetsItem>){
        this.setsList.clear()
        addSetList(setList)
    }

    fun setOnSetClick(onSetClick: (SetsItem)->Unit){
        onSetClickListener = onSetClick
    }

}