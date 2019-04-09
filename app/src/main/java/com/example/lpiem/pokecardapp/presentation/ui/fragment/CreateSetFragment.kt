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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.data.model.User.CardsUser
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.CardListAdapter
import com.example.lpiem.pokecardapp.presentation.ui.adapter.ChooseCardAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.CardListCallback
import com.example.lpiem.pokecardapp.presentation.viewmodel.CardListViewModel
import com.example.lpiem.pokecardapp.presentation.viewmodel.CreateSetViewModel
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.alert_add_set.*
import kotlinx.android.synthetic.main.fragment_card_list.*
import kotlinx.android.synthetic.main.fragment_create_set.*

class CreateSetFragment : BaseFragment<CreateSetViewModel>(), View.OnClickListener {
    private lateinit var adapter: CardListAdapter
    private lateinit var adapterCard: ChooseCardAdapter
    private lateinit var navigator: Navigator
    private var listChooseCard: ArrayList<Card> = ArrayList()
    private var listCard: ArrayList<Card> = ArrayList()
    override val viewModelClass = CreateSetViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CardListAdapter()
        adapterCard = ChooseCardAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_create_set_add.setOnClickListener(this)

        fragment_create_set_recyclerview.layoutManager = GridLayoutManager(context,3)
        fragment_create_set_recycleview_card.layoutManager = GridLayoutManager(context,5)


        fragment_create_set_recyclerview.adapter = adapter
        fragment_create_set_recycleview_card.adapter = adapterCard

        adapter.setOnCardClick { card,check -> onCardClick(card,check) }
        adapterCard.setOnCardClick { card -> onCardChooseClick(card) }

        val updateUser = Observer<User> { postUser ->
            listCard = ArrayList(postUser.cards!!)
            this.updateList(postUser.cards!!)
        }


        val updateError = Observer<ErrorMessage> { postError ->
            if(postError != null){
                showError(postError.message!!)
            }
        }

        val updateSetAdd = Observer<Boolean> { postSetAdd ->
            if(postSetAdd) {
                navigator.displayMySets()
            }
        }

        viewModel.getUser().observe(this,updateUser)
        viewModel.getError().observe(this,updateError)
        viewModel.getSetAdd().observe(this,updateSetAdd)

        viewModel.retrieveUser()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_set, container, false)

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.fragment_create_set_add -> {
                addCard()
            }
        }
    }

    private fun addCard() {
        val alertAdd = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view = factory.inflate(R.layout.alert_add_set, null)
        val nameField: TextInputLayout = view.findViewById(R.id.alert_add_set_name)
        val desciptionField: TextInputLayout = view.findViewById(R.id.alert_add_set_description)

        alertAdd.setView(view)
        alertAdd.setPositiveButton("OK") {
            _, _ ->

            val setsUser = SetsUser()
            setsUser.cards = listChooseCard
            setsUser.title = nameField.editText?.text.toString()
            setsUser.description = desciptionField.editText?.text.toString()
            viewModel.addSet(setsUser)
            navigator.displayMySets()
        }
        alertAdd.setNegativeButton("Cancel") {
            d: DialogInterface, _ ->
            d.dismiss()
        }
        alertAdd.show()
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
            }
        } else {
            listChooseCard.remove(card)
        }
        adapterCard.updateCardList(listChooseCard)
    }

    private fun onCardChooseClick(card: Card) {
        listChooseCard.remove(card)
        adapterCard.updateCardList(listChooseCard)
    }

    companion object {
        fun newInstance(): CreateSetFragment =  CreateSetFragment()
    }
}

