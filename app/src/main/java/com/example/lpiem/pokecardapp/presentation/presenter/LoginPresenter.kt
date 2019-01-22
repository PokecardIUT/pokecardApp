package com.example.lpiem.pokecardapp.presentation.presenter

import android.os.Bundle
import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONException

class LoginPresenter(var callback:LoginCallback) {

    var repository = PokeApplication.getInstance().repository

    fun connexionWithEmail(username: String, password: String){
        if(username.isEmpty() || password.isEmpty()){
            callback.showError("Un champs n'a pas été remplis")
            return
        }

        repository.connexionWithEmail(username,password).subscribe {
            res -> Log.d("ConnexionEmail", res.toString())
            if(res.success != null){
                //TODO add function to write token in file
                callback.goToDeckListActivity("email", username, "")
            }
            else{

            }
        }
    }

    fun connectionWithFb(callbackManager: CallbackManager, loginButton: LoginButton) {
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
               getInfoFb(loginResult.accessToken)

            }

            override fun onCancel() {
                Log.d("FbError", "Cancel")
            }

            override fun onError(exception: FacebookException) {
                Log.d("FbError", exception.message)
            }
        })
    }

    fun isLoggedFb(): Boolean = repository.isLoggedFb()


    fun getInfoFb(token: AccessToken) {
        val request = GraphRequest.newMeRequest(
                token
        ) { `object`, response ->
            Log.v("LoginActivity", response.toString())
            try {
                val email = `object`.getString("email")
                var name = `object`.getString("name")
                name = name.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                callback.goToDeckListActivity("facebook",name,email)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "name,email")
        request.parameters = parameters
        request.executeAsync()
    }
}