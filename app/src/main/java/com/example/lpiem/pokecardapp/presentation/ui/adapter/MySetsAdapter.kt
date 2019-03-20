package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.MySetsViewHolder
import com.example.lpiem.pokecardapp.presentation.utils.inflate

class MySetsAdapter: RecyclerView.Adapter<MySetsViewHolder>() {

    private val setsList: MutableList<SetsUser> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySetsViewHolder {
        val inflatedView = parent.inflate(R.layout.adapter_my_sets, false)
        return MySetsViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = setsList.size

    override fun onBindViewHolder(holder: MySetsViewHolder, position: Int) {
        val userSet = setsList[position]

        holder.bindSet(userSet)
    }

    fun addSetList(setsList : List<SetsUser>){
        this.setsList.addAll(setsList)
        notifyDataSetChanged()
    }

    fun updateSetList(setList: List<SetsUser>){
        this.setsList.clear()
        addSetList(setList)
    }

}