package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.lpiem.pokecardapp.R
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.example.lpiem.pokecardapp.presentation.presenter.LoginPresenter
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_connection.*


/*TODO Archi mvvm, button facebook and google, delete onActivityResult ?
  TODO Rename var of button in layout and ui...
*/

class LoginActivity : AppCompatActivity(), LoginCallback, View.OnClickListener {


    val viewModel: LoginPresenter = LoginPresenter(this)
    lateinit var callbackManagerFacebook: CallbackManager
    lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == BUTTON_GOOGLE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            callbackManagerFacebook.onActivityResult(requestCode, resultCode, data)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

        callbackManagerFacebook = CallbackManager.Factory.create()

        buttonConnectWithEmail.setOnClickListener(this)
        buttonConnectionWithFb.setReadPermissions("public_profile", "email")
        buttonConnectionWithFb.setOnClickListener(this)
        buttonConnectionWithGoogle.setOnClickListener(this)

        if (viewModel.isLoggedFb()) {

            viewModel.getInfoFb(AccessToken.getCurrentAccessToken())

        }



        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonConnectWithEmail -> {
                viewModel.connexionWithEmail(usernameField.editText?.text.toString(), passwordField.editText?.text.toString())
            }
            R.id.buttonConnectionWithFb -> {
                viewModel.connectionWithFb(callbackManagerFacebook, buttonConnectionWithFb)
            }
            R.id.buttonConnectionWithGoogle -> {
                val signInIntent = mGoogleSignInClient?.signInIntent
                startActivityForResult(signInIntent, BUTTON_GOOGLE)
            }
        }

    }

    override fun showError(message: String) {
        Log.d("ConnexionEmail", "Error")
    }

    override fun goToPokeCardActivity() {
        val deckListActivityIntent = Intent(this, PokeCardActivity::class.java)
        startActivity(deckListActivityIntent)
    }

    override fun getContext(): Context = this

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult<ApiException>(ApiException::class.java)
            val personEmail = account!!.email
            val personName = account.givenName


            goToPokeCardActivity()
        } catch (e: ApiException) {

            Log.w("mlk", "signInResult:failed code=" + e.statusCode)
        }

    }

    companion object {
        private const val BUTTON_GOOGLE = 9001
    }

}
