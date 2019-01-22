package com.example.lpiem.pokecardapp.presentation.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.navigator.Navigator
import com.example.lpiem.pokecardapp.presentation.presenter.AccountPresenter
import com.example.lpiem.pokecardapp.presentation.ui.activity.LoginActivity
import com.facebook.AccessToken.*
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment(), View.OnClickListener{

    private lateinit var presenter: AccountPresenter
    private lateinit var navigator: Navigator


    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = AccountPresenter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        disconnectButton.setOnClickListener(this)

        navigator = Navigator(fragmentManager!!)

        usernameTv.text = presenter.getName()
        passwordTv.text = presenter.getPassword()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)

    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.disconnectButton -> {

                if (presenter.isLoggedFb()) {

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
