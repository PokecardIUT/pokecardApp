package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.viewmodel.CardListViewModel
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.CardListCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card_list.*

private const val INTENT_SET_ID_EXTRA = "INTENT_SET_ID_EXTRA"

class CardListFragment : BaseFragment<CardListViewModel>(), CardListCallback {
    private lateinit var adapter: CardListAdapter
    private lateinit var navigator: Navigator
    private lateinit var setCode: String
    override val viewModelClass = CardListViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CardListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_card_list_recyclerview.layoutManager = GridLayoutManager(context,3)

        fragment_card_list_recyclerview.adapter = adapter

        adapter.setOnCardClick { onCardClick(it) }

        val updateCardList = Observer<List<Card>>{
            postList -> updateList(postList)
        }

        viewModel.getCardList().observe(this, updateCardList)

        viewModel.getCardBySets(setCode)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setCode = arguments?.getString(INTENT_SET_ID_EXTRA)!!

        return inflater.inflate(R.layout.fragment_card_list, container, false)

    }

    override fun updateList(cardList: List<Card>) {
        adapter.updateCardList(cardList)
    }

    private fun onCardClick(card: Card){
        Log.d("Card", card.id)
        val alertAdd = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view = factory.inflate(R.layout.alert_one_card, null)
        alertAdd.setView(view)
        val cardImageView: ImageView = view.findViewById(R.id.dialog_imageview)
        Picasso.get().load(card.imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
        alertAdd.setPositiveButton("OK") {
            d: DialogInterface, _ ->
            d.dismiss()
        }
        alertAdd.show()
    }

    companion object {

        fun newInstance(setCode: String): CardListFragment {
            val fragment = CardListFragment()
            val args = Bundle()
            args.putString(INTENT_SET_ID_EXTRA,setCode)
            fragment.arguments = args

            return fragment
        }


    }

}