package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.RankingAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.TradeAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.TradeViewModel
import kotlinx.android.synthetic.main.fragment_trade.*

class TradeFragment : BaseFragment<TradeViewModel>() {

    private lateinit var adapter: TradeAdapter
    private lateinit var adapterCardUser: CardListAdapter
    override val viewModelClass = TradeViewModel::class
    private lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = TradeAdapter()
        adapterCardUser = CardListAdapter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_trade_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_trade_recyclerview.adapter = adapter

        adapter.setOnUserClick { onUserClick(it) }

        val updateUserList = Observer<List<User>>{
            postList -> updateList(postList)
        }

        viewModel.getUserList().observe(this, updateUserList)

        viewModel.getUsers()

    }


    fun onUserClick(user: User) {
        fragment_trade_recyclerview.adapter = adapterCardUser

        adapterCardUser.updateCardList(user.cards!!)
    }

    fun updateList(userList: List<User>) {
        adapter.updateUserList(userList)
    }

    companion object {

        fun newInstance(): TradeFragment {

            return TradeFragment()
        }


    }
}