package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.example.lpiem.pokecardapp.presentation.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_connection.*

/*TODO Archi mvvm, button facebook and google, delete onActivityResult ?
  TODO Rename var of button in layout and ui...
*/

class LoginActivity : AppCompatActivity(), LoginCallback, View.OnClickListener {

    val viewModel: LoginViewModel = LoginViewModel(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)
        buttonConnectWithEmail.setOnClickListener(this)
        viewModel.initSharedPreferences()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonConnectWithEmail -> {
               viewModel.connexionWithEmail(usernameField.text.toString(),passwordField.text.toString())
            }
        }

    }

    override fun showError(message: String) {
       Log.d("ConnexionEmail", "Error")
    }

    override fun goToDeckListActivity() {
        //TODO Ajoute du navigator
        val deckListActivityIntent = Intent(this, DeckListActivity::class.java)
        startActivity(deckListActivityIntent)

    }

    override fun getContext(): Context = this

}
