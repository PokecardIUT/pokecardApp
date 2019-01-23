package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.Deck.SetsItem
import com.example.lpiem.pokecardapp.data.model.Deck.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.presenter.RankingPresenter
import com.example.lpiem.pokecardapp.presentation.presenter.SetsListPresenter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.RankingAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.SetsListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.view.RankingCallback
import com.example.lpiem.pokecardapp.presentation.ui.view.SetsListCallback
import kotlinx.android.synthetic.main.fragment_sets_list.*


class RankingFragment : Fragment(), RankingCallback {

    private lateinit var presenter: RankingPresenter
    private lateinit var adapter: RankingAdapter
    private lateinit var navigator: Navigator


    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = RankingPresenter(this)
        adapter = RankingAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_user_list_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_user_list_recyclerview.adapter = adapter

        presenter.getRank()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)

    }

    override fun updateList(userList: List<User>) {
        adapter.updateUserList(userList)
    }

    private fun onUserClick(user: User){
        Log.d("mlk", "switch")
    }

    companion object {

        fun newInstance(): RankingFragment =  RankingFragment()


    }
}
