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
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.viewmodel.RankingViewModel
import com.example.lpiem.pokecardapp.presentation.ui.adapter.RankingAdapter
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.RankingCallback
import kotlinx.android.synthetic.main.fragment_user_list.*


class RankingFragment : BaseFragment<RankingViewModel>(), RankingCallback {
    private lateinit var adapter: RankingAdapter
    private lateinit var navigator: Navigator
    override val viewModelClass = RankingViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = RankingAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)

        navigator = Navigator(fragmentManager!!)

        fragment_user_list_recyclerview.layoutManager = LinearLayoutManager(context)

        fragment_user_list_recyclerview.adapter = adapter

        val updateUserList = Observer<List<User>>{
            postList -> updateList(postList)
        }

        viewModel.getUserList().observe(this, updateUserList)

        viewModel.getRank()
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
