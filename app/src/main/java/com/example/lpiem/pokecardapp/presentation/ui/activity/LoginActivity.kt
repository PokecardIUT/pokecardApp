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
import kotlinx.android.synthetic.main.activity_connection.*


/*TODO Archi mvvm, button facebook and google, delete onActivityResult ?
  TODO Rename var of button in layout and ui...
*/

class LoginActivity : AppCompatActivity(), LoginCallback, View.OnClickListener {


    val viewModel: LoginPresenter = LoginPresenter(this)
    lateinit var callbackManager: CallbackManager

    /* private var mGoogleSignInClient: GoogleSignInClient? = null
     private var loginButton: LoginButton? = null
 */
    /*   public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
           Log.d("mlk","2")
           // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
           if (requestCode == BUTTON_GOOGLE) {
               // The Task returned from this call is always completed, no need to attach
               // a listener.
               val task = GoogleSignIn.getSignedInAccountFromIntent(data)
               handleSignInResult(task)
           } else if (requestCode == SIGN_OUT) {
               signOut()
           } else {
               callbackManager!!.onActivityResult(requestCode, resultCode, data)
           }
       }
   */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

        callbackManager = CallbackManager.Factory.create()

        buttonConnectWithEmail.setOnClickListener(this)
        buttonConnectionWithFb.setReadPermissions("public_profile", "email")
        buttonConnectionWithFb.setOnClickListener(this)

        if (viewModel.isLoggedFb()) {

            viewModel.getInfoFb(AccessToken.getCurrentAccessToken())

        }


        /*
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        findViewById<View>(R.id.sign_in_button).setOnClickListener(this)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        callbackManager = CallbackManager.Factory.create()
        // If using in a fragment
        //loginButton.setFragment(this);
        loginButton = findViewById(R.id.login_button)
        loginButton!!.setReadPermissions(Arrays.asList(
                "public_profile", "email"))
        loginButton!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                ) { `object`, response ->
                    Log.v("LoginActivity", response.toString())

                    // PokeApplication code
                    try {
                        val email = `object`.getString("email")
                        var name = `object`.getString("name")
                        name = name.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]

                        gotoApiActivity(name, email)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "name,email")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {

            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })*/
    }

    override fun onResume() {
        super.onResume()

        /* val acct = GoogleSignIn.getLastSignedInAccount(this)
         if (acct != null) {
             val personEmail = acct.email
             val personName = acct.givenName
         }*/
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonConnectWithEmail -> {
                viewModel.connexionWithEmail(usernameField.editText?.text.toString(), passwordField.editText?.text.toString())
            }
            R.id.buttonConnectionWithFb -> {
                viewModel.connectionWithFb(callbackManager, buttonConnectionWithFb)
            }
        }

    }

    override fun showError(message: String) {
        Log.d("ConnexionEmail", "Error")
    }

    override fun goToDeckListActivity(method: String, name: String, email: String) {
        //TODO Ajoute du navigator
        val deckListActivityIntent = Intent(this, SearchCardActivity::class.java)
        deckListActivityIntent.putExtra("method", method)
        deckListActivityIntent.putExtra("name", name)
        deckListActivityIntent.putExtra("email", email)
        startActivity(deckListActivityIntent)
    }

    override fun getContext(): Context = this


    /*
        private fun signIn() {
            Log.d("mlk", "1")
            val signInIntent = mGoogleSignInClient?.signInIntent
            startActivityForResult(signInIntent, BUTTON_GOOGLE)
        }

        fun signOut() {
            mGoogleSignInClient!!.signOut()
                    .addOnCompleteListener(this) { }
        }

        fun revokeAccess() {
            mGoogleSignInClient!!.revokeAccess()
                    .addOnCompleteListener(this) { }
        }

        private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account = completedTask.getResult<ApiException>(ApiException::class.java)
                val personEmail = account!!.email
                val personName = account.givenName

                Toast.makeText(this, "Intent Google", Toast.LENGTH_SHORT)

                gotoApiActivity(personName, personEmail)
                // Signed in successfully, show authenticated UI.
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            }

        }

        private fun gotoApiActivity(name: String?, email: String?) {
            val signInIntent = Intent(this@LoginActivity, DeckListActivity::class.java)
            signInIntent.putExtra("name", name)
            signInIntent.putExtra("email", email)
            startActivityForResult(signInIntent, SIGN_IN_GOOGLE)
        }
    */
    companion object {
        private val SIGN_OUT = 8000
    }

}
