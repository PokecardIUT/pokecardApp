package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.Login.ResultCode
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.example.lpiem.pokecardapp.presentation.viewmodel.LoginViewModel
import com.example.lpiem.pokecardapp.presentation.viewmodel.SignUpViewModel
import com.example.lpiem.pokecardapp.presentation.viewmodel.factory.ViewModelFactory
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_connection.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    var viewModelFactory: ViewModelFactory = ViewModelFactory()
    lateinit var viewModel: SignUpViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)



        val updateSuccess = Observer<ResultCode> { postUser ->
            if(postUser != null){
                showError("Vous etes inscrit, Connectez vous !")
                val loginActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginActivity)
            }
        }

        val updateError = Observer<ErrorMessage> { postError ->
            if(postError != null){
                showError(postError.message!!)
            }
        }

        viewModel.getError().observe(this,updateError)
        viewModel.getSuccess().observe(this,updateSuccess)

        buttonSignUpActivity.setOnClickListener(this)
    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonSignUpActivity -> {
                viewModel.signup(usernameSignUpField.editText?.text.toString(),passwordSignUpField.editText?.text.toString(), confirmPasswordField.editText?.text.toString())
            }
        }

    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun goToPokeCardActivity() {
        val deckListActivityIntent = Intent(this, PokeCardActivity::class.java)
        startActivity(deckListActivityIntent)
    }

    fun getContext(): Context = this


}