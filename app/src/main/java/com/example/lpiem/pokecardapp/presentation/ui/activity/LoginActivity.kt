package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.User.User
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.example.lpiem.pokecardapp.presentation.viewmodel.LoginViewModel
import com.example.lpiem.pokecardapp.presentation.viewmodel.factory.ViewModelFactory
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_connection.*


/*TODO Archi mvvm, button facebook and google, delete onActivityResult ?
  TODO Rename var of button in layout and ui...
*/

class LoginActivity : AppCompatActivity(), LoginCallback, View.OnClickListener {


    var viewModelFactory: ViewModelFactory = ViewModelFactory()
    lateinit var viewModel: LoginViewModel

    lateinit var callbackManagerFacebook: CallbackManager
    lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == BUTTON_GOOGLE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.getInfoGoogle(task)
        } else {
            callbackManagerFacebook.onActivityResult(requestCode, resultCode, data)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        callbackManagerFacebook = CallbackManager.Factory.create()

        buttonConnectWithEmail.setOnClickListener(this)
        buttonConnectionWithFb.setReadPermissions("public_profile", "email")
        buttonConnectionWithFb.setOnClickListener(this)
        buttonConnectionWithGoogle.setOnClickListener(this)
        buttonSignup.setOnClickListener(this)

        val userLoggedFb = Observer<Boolean> { postBool ->
            if(postBool){
                viewModel.getInfoFb(AccessToken.getCurrentAccessToken())
            }
        }

        viewModel.isLoggedFb()

        val updateUser = Observer<User> { postUser ->
            if(postUser != null){
                goToPokeCardActivity()
            }
        }

        val updateError = Observer<ErrorMessage> { postError ->
            if(postError != null){
                showError(postError.message!!)
            }
        }

        viewModel.getUser().observe(this,updateUser)
        viewModel.getIsLoggedFb().observe(this,userLoggedFb)
        viewModel.getError().observe(this,updateError)

        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonConnectWithEmail -> {
                Log.d("mlk","connexion")
                viewModel.connexionWithEmail(usernameField.editText?.text.toString(), passwordField.editText?.text.toString())
            }
            R.id.buttonConnectionWithFb -> {
                viewModel.connectionWithFb(callbackManagerFacebook, buttonConnectionWithFb)
            }
            R.id.buttonConnectionWithGoogle -> {
                val signInIntent = mGoogleSignInClient?.signInIntent
                startActivityForResult(signInIntent, BUTTON_GOOGLE)
            }
            R.id.buttonSignup -> {
                val signupActivity = Intent(this, SignUpActivity::class.java)
                startActivity(signupActivity)
            }
        }

    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goToPokeCardActivity() {
        val deckListActivityIntent = Intent(this, PokeCardActivity::class.java)
        startActivity(deckListActivityIntent)
    }

    override fun getContext(): Context = this


    companion object {
        private const val BUTTON_GOOGLE = 9001
    }

}
