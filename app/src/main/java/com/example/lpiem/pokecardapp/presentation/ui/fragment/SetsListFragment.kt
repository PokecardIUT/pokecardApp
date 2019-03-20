package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.viewmodel.SetsListViewModel
import com.example.lpiem.pokecardapp.presentation.ui.adapter.SetsListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback
import kotlinx.android.synthetic.main.fragment_sets_list.*

private const val INTENT_SHOP_EXTRA = "INTENT_SHOP_EXTRA"

class SetsListFragment() : SetsListCallback, BaseFragment<SetsListViewModel>() {

    private lateinit var adapter: SetsListAdapter
    private lateinit var navigator: Navigator
    override val viewModelClass = SetsListViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = SetsListAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_set_list_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_set_list_recyclerview.adapter = adapter

        adapter.setOnSetClick { onSetClick(it) }

        var updateSets = Observer<List<SetsItem>> { postList ->
            updateList(postList)

        }

        viewModel.getListSets().observe(this,updateSets);

        viewModel.getSets()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sets_list, container, false)

    }

    override fun updateList(setsList: List<SetsItem>) {
        adapter.updateSetList(setsList)
    }

    private fun onSetClick(set: SetsItem){
        if(arguments?.getString(INTENT_SHOP_EXTRA)!! == "shop") {
            navigator.displayShop(set.code!!)
        }
        else {
            navigator.displayCardList(set.code!!)
        }
    }

    companion object {

        fun newInstance(identifier: String = ""): SetsListFragment {
            val fragment = SetsListFragment()
            val args = Bundle()
            args.putString(INTENT_SHOP_EXTRA,identifier)
            fragment.arguments = args
            return fragment
        }


    }
}
