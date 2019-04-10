package com.example.lpiem.pokecardapp.presentation.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.CardListViewHolder
import com.example.lpiem.pokecardapp.presentation.ui.viewholder.ChooseCardViewHolder
import com.example.lpiem.pokecardapp.presentation.utils.inflate

class ChooseCardAdapter: RecyclerView.Adapter<ChooseCardViewHolder>() {

    private val cardList: MutableList<Card> = mutableListOf()
    private  var onCardClickListener: ((Card)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCardViewHolder {
        val inflatedView = parent.inflate(R.layout.adapter_card_create_set, false)
        return ChooseCardViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: ChooseCardViewHolder, position: Int) {
        val itemSet = cardList[position]

        holder.bindSet(itemSet,onCardClickListener)
    }

    fun addCardList(cardList : List<Card>){
        this.cardList.addAll(cardList)
        notifyDataSetChanged()
    }

    fun updateCardList(cardList: List<Card>){
        this.cardList.clear()
        addCardList(cardList)
    }

    fun setOnCardClick(onCardClick: (Card)->Unit){
        onCardClickListener = onCardClick
    }
}