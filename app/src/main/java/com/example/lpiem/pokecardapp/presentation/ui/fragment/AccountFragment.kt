package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.viewmodel.AccountViewModel
import com.example.lpiem.pokecardapp.presentation.ui.activity.LoginActivity
import com.example.lpiem.pokecardapp.presentation.ui.fragment.base.BaseFragment
import com.example.lpiem.pokecardapp.presentation.ui.view.AccountCallback
import com.facebook.AccessToken.*
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : BaseFragment<AccountViewModel>(), AccountCallback, View.OnClickListener{


    private lateinit var navigator: Navigator
    override val viewModelClass = AccountViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        disconnectButton.setOnClickListener(this)

        navigator = Navigator(fragmentManager!!)

        val updateUser = Observer<User> { postUser ->
            showUserAccount(postUser)
        }

        viewModel.getUser().observe(this,updateUser)

        viewModel.retrieveUser()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)

    }

    override fun showUserAccount(user: User) {
        usernameTv.text = user.name
    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.disconnectButton -> {

                if (viewModel.isLoggedFb()) {

                    setCurrentAccessToken(null)

                    LoginManager.getInstance().logOut()

                }

                startActivity(Intent(activity, LoginActivity::class.java))

            }

        }

    }

    companion object {
        fun newInstance(): AccountFragment =  AccountFragment()
    }
}
