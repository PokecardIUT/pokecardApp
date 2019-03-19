package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlin.concurrent.timerTask
import android.content.DialogInterface
import android.app.AlertDialog
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.alert_cards.*


private const val INTENT_SET_ID_EXTRA = "INTENT_SET_ID_EXTRA"

class ShopFragment : BaseFragment<ShopViewModel>(), View.OnClickListener {
    private lateinit var navigator: Navigator
    private lateinit var id: String
    private lateinit var nbCard: String
    private var cards: MutableList<Card> = mutableListOf()
    override val viewModelClass = ShopViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        card1.setOnClickListener(this)
        card2.setOnClickListener(this)
        card3.setOnClickListener(this)

        val updateCardList = Observer<List<Card>>{
            postList -> cards.addAll(postList)
            showCards()
        }


        viewModel.getCardList().observe(this, updateCardList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        id = arguments?.getString(INTENT_SET_ID_EXTRA)!!
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.card1 -> {
                nbCard = "1"
                viewModel.getRandomCard(id, nbCard)
            }
            R.id.card2 ->  {
                nbCard = "3"
                viewModel.getRandomCard(id, nbCard)
            }
            R.id.card3 ->  {
                nbCard = "5"
                viewModel.getRandomCard(id, nbCard)
            }
        }
    }

    private fun showCards() {
        val alertadd = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view = factory.inflate(R.layout.alert_cards, null)
        alertadd.setView(view)
        val cardImageView: ImageView = view.findViewById(R.id.dialog_imageview)
        Log.d("cards", cards[0].toString())
        Picasso.get().load(cards[0].imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
        var i = 0
        alertadd.setPositiveButton("Next") {
            _, _ ->
            ++i
            if (i < cards.count()) {
                Picasso.get().load(cards[i].imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
            }
        }
        alertadd.show()
    }

    companion object {

        fun newInstance(id: String): ShopFragment {
            val fragment = ShopFragment()
            val args = Bundle()
            args.putString(INTENT_SET_ID_EXTRA, id)
            fragment.arguments = args

            return fragment
        }


    }


}