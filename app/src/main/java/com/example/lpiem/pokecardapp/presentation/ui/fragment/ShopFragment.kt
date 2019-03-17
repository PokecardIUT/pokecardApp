package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_card_list.*

private const val INTENT_SET_ID_EXTRA = "INTENT_SET_ID_EXTRA"

class ShopFragment : BaseFragment<ShopViewModel>() {
    private lateinit var adapter: CardListAdapter
    private lateinit var navigator: Navigator
    private lateinit var id: String
    private lateinit var nbCard: String
    override val viewModelClass = ShopViewModel::class

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

        adapter.setOnCardClick { onSetClick(it) }

        viewModel.getRandomCard(id, nbCard)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        id = arguments?.getString(INTENT_SET_ID_EXTRA)!!

        return inflater.inflate(R.layout.fragment_card_list, container, false)

    }

    private fun onSetClick(set: Card){
        Log.d("onSetClick", set.name)
    }

    companion object {

        fun newInstance(id: String): CardListFragment {
            val fragment = CardListFragment()
            val args = Bundle()
            args.putString(INTENT_SET_ID_EXTRA, id)
            fragment.arguments = args

            return fragment
        }


    }
}