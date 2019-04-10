package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.RankingAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.TradeAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.TradeViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_trade.*

class TradeFragment : BaseFragment<TradeViewModel>(), View.OnClickListener {


    private lateinit var adapter: TradeAdapter
    private lateinit var adapterCardUser: CardListAdapter
    override val viewModelClass = TradeViewModel::class
    private lateinit var navigator: Navigator
    private lateinit var userChoose: User
    private var userChooseCard: Card? = null
    private lateinit var cardChoose: Card
    private lateinit var currentUser: User

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

        fragment_trade_button.setOnClickListener(this)

        fragment_trade_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_trade_recyclerview.adapter = adapter

        adapter.setOnUserClick { onUserClick(it) }

        val updateUserList = Observer<List<User>>{
            postList -> updateList(postList)
        }

        val updateUser = Observer<User> { postUser ->
            currentUser = postUser
        }


        val updateError = Observer<ErrorMessage> { postError ->
            if(postError != null){
                showError(postError.message!!)
            }
        }

        val tradeFinish = Observer<Boolean> { postTrade ->
            if(postTrade) {
                navigator.displayMySets()
            }
        }

        viewModel.getUser().observe(this,updateUser)
        viewModel.getError().observe(this,updateError)
        viewModel.isTradeActive().observe(this,tradeFinish)

        viewModel.retrieveUser()

        viewModel.getUserList().observe(this, updateUserList)

        viewModel.getUsers()

    }


    fun onUserClick(user: User) {
        userChoose = user!!

        fragment_trade_recyclerview.adapter = adapterCardUser

        fragment_trade_recyclerview.layoutManager = GridLayoutManager(context,3)

        adapterCardUser.setOnCardClick { onCardClick(it) }

        adapterCardUser.updateCardList(user.cards!!)
    }

    fun onCardClick(card: Card) {

        if(userChooseCard != null) {
            cardChoose = card
            fragment_trade_button.visibility = View.VISIBLE
        } else {
            userChooseCard = card
            adapterCardUser.updateCardList(currentUser.cards!!)
        }
    }

    fun updateList(userList: List<User>) {
        adapter.updateUserList(userList)
    }

    fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.fragment_trade_button -> {
                val userList = listOf<String>(userChoose.username!!, currentUser.username!!)
                val cardList = listOf<String>(Gson().toJson(userChooseCard!!), Gson().toJson(cardChoose))
                 viewModel.trade(userList,cardList)
            }
        }
    }

    companion object {

        fun newInstance(): TradeFragment {

            return TradeFragment()
        }


    }
}