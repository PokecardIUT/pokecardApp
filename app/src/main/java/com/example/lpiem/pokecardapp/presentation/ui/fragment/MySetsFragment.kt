package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.User.SetsUser
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.ui.adapter.MySetsAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.viewmodel.MySetsViewModel
import kotlinx.android.synthetic.main.fragment_my_sets.*

class MySetsFragment : BaseFragment<MySetsViewModel>(), View.OnClickListener{

    private lateinit var adapter: MySetsAdapter
    private lateinit var navigator: Navigator
    override val viewModelClass = MySetsViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = MySetsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_my_sets_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_my_sets_recyclerview.adapter = adapter

        menu_shop.setOnClickListener(this)
        menu_add_sets.setOnClickListener(this)

        val updateUser = Observer<User> { postUser ->
            this.updateList(postUser.sets!!)
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

    fun updateList(setsList: List<SetsUser>) {
        adapter.updateSetList(setsList)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_sets, container, false)

    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.menu_add_sets -> {
                Log.d("mlk","set")
            }

            R.id.menu_shop -> {
                navigator.displaySetsListShop()
                activity?.title = "Boutique"
            }

            R.id.menu_trade -> {
                navigator.displayTrade()
                activity?.title = "Echange de cartes"
            }
        }

    }

    fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): MySetsFragment =  MySetsFragment()
    }
}
