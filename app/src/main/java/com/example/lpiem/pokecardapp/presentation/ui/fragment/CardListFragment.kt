package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.presenter.CardListPresenter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.view.CardListCallback
import kotlinx.android.synthetic.main.fragment_card_list.*

private const val INTENT_SET_ID_EXTRA = "INTENT_SET_ID_EXTRA"

class CardListFragment: Fragment(), CardListCallback {
    private lateinit var presenter: CardListPresenter
    private lateinit var adapter: CardListAdapter
    private lateinit var navigator: Navigator
    private lateinit var setCode: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = CardListPresenter(this)
        adapter = CardListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_card_list_recyclerview.layoutManager = GridLayoutManager(context,3)

        fragment_card_list_recyclerview.adapter = adapter

        adapter.setOnCardClick { onSetClick(it) }

        presenter.getCardBySets(setCode)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setCode = arguments?.getString(INTENT_SET_ID_EXTRA)!!

        return inflater.inflate(R.layout.fragment_card_list, container, false)

    }

    override fun updateList(cardList: List<Card>) {
        adapter.updateCardList(cardList)
    }

    private fun onSetClick(set: Card){
        Log.d("mlk", "switch")
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