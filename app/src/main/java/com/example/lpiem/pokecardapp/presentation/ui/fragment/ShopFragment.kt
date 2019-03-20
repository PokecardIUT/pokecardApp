package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.SetCard.Card
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.*
import android.app.AlertDialog
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.lpiem.pokecardapp.data.model.SetCard.CardsCount
import com.squareup.picasso.Picasso

private const val INTENT_SET_ID_EXTRA = "INTENT_SET_ID_EXTRA"

class ShopFragment : BaseFragment<ShopViewModel>(), View.OnClickListener {
    private lateinit var navigator: Navigator
    private lateinit var id: String
    private lateinit var nbCard: String
    private var cards: MutableList<Card> = mutableListOf()
    private lateinit var cardsCount: CardsCount
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

        val updateCardsCount = Observer<CardsCount>{
            postCount -> cardsCount = postCount
            if(cardsCount.result!! > 5) {
                card3.visibility = View.VISIBLE
                tvCard3.visibility = View.VISIBLE
            }
            if(cardsCount.result!! > 3) {
                card2.visibility = View.VISIBLE
                tvCard2.visibility = View.VISIBLE
            }
            if(cardsCount.result!! > 1) {
                card1.visibility = View.VISIBLE
                tvCard1.visibility = View.VISIBLE
            }
            if(cardsCount.result!! == 0) {
                tvAllCards.visibility = View.VISIBLE
            }
        }

        viewModel.getCardsCount().observe(this, updateCardsCount)
        viewModel.getCardsCount(id)
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
        val alertAdd = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view = factory.inflate(R.layout.alert_cards, null)
        alertAdd.setView(view)
        val cardImageView: ImageView = view.findViewById(R.id.dialog_imageview)
        val prev: Button = view.findViewById(R.id.prev)
        val next: Button = view.findViewById(R.id.next)
        val nbCardLbl: TextView = view.findViewById(R.id.nbCardLbl)
        var index = 0
        nbCardLbl.text = "${(index + 1)} / ${cards.count()}"
        Picasso.get().load(cards[0].imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
        alertAdd.setPositiveButton("OK") {
            _, _ ->
            cards.clear()

            navigator.displaySetsList("shop")
        }
        next.setOnClickListener {
            if (index + 1 < cards.count()) {
                ++index
                Picasso.get().load(cards[index].imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
                nbCardLbl.text = "${(index + 1)} / ${cards.count()}"
            }
        }
        prev.setOnClickListener {
            if (index - 1 >= 0) {
                --index
                Picasso.get().load(cards[index].imageUrlHiRes).placeholder(R.mipmap.card_hide).into(cardImageView)
                nbCardLbl.text = "${(index + 1)} / ${cards.count()}"
            }
        }
        alertAdd.show()
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