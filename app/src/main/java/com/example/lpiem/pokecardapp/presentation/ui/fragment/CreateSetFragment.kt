package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.User.CardsUser
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.CardListCallback
import com.example.lpiem.pokecardapp.presentation.viewmodel.CardListViewModel
import com.example.lpiem.pokecardapp.presentation.viewmodel.CreateSetViewModel
import kotlinx.android.synthetic.main.fragment_card_list.*
import kotlinx.android.synthetic.main.fragment_create_set.*

class CreateSetFragment : BaseFragment<CreateSetViewModel>() {
    private lateinit var adapter: CardListAdapter
    private lateinit var navigator: Navigator
    private var listChooseCard: ArrayList<Card> = ArrayList()
    private var listCard: ArrayList<Card> = ArrayList()
    override val viewModelClass = CreateSetViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CardListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_create_set_recyclerview.layoutManager = GridLayoutManager(context,3)

        fragment_create_set_recyclerview.adapter = adapter

        adapter.setOnCardClick { card,check -> onCardClick(card,check) }

        val updateUser = Observer<User> { postUser ->
            listCard = ArrayList(postUser.cards!!)
            this.updateList(postUser.cards!!)
        }


        val updateError = Observer<ErrorMessage> { postError ->
            if(postError != null){
                showError(postError.message!!)
            }
        }

        viewModel.getUser().observe(this,updateUser)
        viewModel.getError().observe(this,updateError)

        viewModel.retrieveUser()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_set, container, false)

    }

    fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun updateList(cardList: List<Card>) {
        adapter.updateCardList(cardList)
    }

    private fun onCardClick(card: Card, imageView: ImageView){
        if(imageView.visibility == View.INVISIBLE) {
            if (listChooseCard.count() < 5) {
                listChooseCard.add(card)
                imageView.visibility = View.VISIBLE

            }
        } else {
            listChooseCard.remove(card)
            imageView.visibility = View.INVISIBLE
        }
    }


    companion object {
        fun newInstance(): CreateSetFragment =  CreateSetFragment()
    }
}

