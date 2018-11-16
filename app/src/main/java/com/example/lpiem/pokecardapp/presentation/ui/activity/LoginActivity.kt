package com.example.lpiem.pokecardapp.presentation.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import com.example.lpiem.pokecardapp.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

import org.json.JSONException

import java.util.Arrays

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var callbackManager: CallbackManager? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var loginButton: LoginButton? = null

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else if (requestCode == SIGN_OUT) {
            val accessToken = AccessToken.getCurrentAccessToken()
            val isLoggedIn = accessToken != null && !accessToken.isExpired
            if (isLoggedIn) {
                AccessToken.setCurrentAccessToken(null)
                LoginManager.getInstance().logOut()
            }
            signOut()
        } else {
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

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
        })
    }

    override fun onResume() {
        super.onResume()

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personEmail = acct.email
            val personName = acct.givenName
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
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
            val account = completedTask.getResult<ApiException>(ApiException::class.java!!)
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
        val SignInIntent = Intent(this@LoginActivity, DeckListActivity::class.java)
        SignInIntent.putExtra("name", name)
        SignInIntent.putExtra("email", email)
        startActivityForResult(SignInIntent, SIGN_IN_GOOGLE)
    }

    companion object {

        private val RC_SIGN_IN = 9001
        private val SIGN_IN_FB = 9002
        private val SIGN_IN_GOOGLE = 9003
        private val SIGN_OUT = 8000
        private val TAG = "MainActivity"
    }

}
