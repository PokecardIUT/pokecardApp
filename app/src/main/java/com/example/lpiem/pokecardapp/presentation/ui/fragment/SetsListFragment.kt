package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.presentation.presenter.SetsListPresenter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.DeckListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.SetsListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback
import kotlinx.android.synthetic.main.fragment_sets_list.*


class SetsListFragment : Fragment(), SetsListCallback {

    private lateinit var presenter: SetsListPresenter
   // private lateinit var adapter: SetsListAdapter
     private lateinit var adapter: DeckListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = SetsListPresenter(this)
       // adapter = SetsListAdapter()
        adapter = DeckListAdapter(ArrayList<SetsItem?>() ,context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        fragment_set_list_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_set_list_recyclerview.adapter = adapter

        presenter.getDecks()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sets_list, container, false)

    }

    override fun updateList(setsList: List<SetsItem>) {
        //adapter.updateSetList(setsList)
        adapter.items = setsList
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): SetsListFragment =  SetsListFragment()


    }
}
