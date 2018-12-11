package com.example.lpiem.pokecardapp.presentation.viewModel

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONException
import java.util.*

class LoginViewModel(var callback:LoginCallback) {

    var repository = PokeApplication.app.repository
    lateinit var callbackManager: CallbackManager
    var permissionNeeds: List<String> = Arrays.asList("public_profile", "email")

    fun connexionWithEmail(username: String, password: String){
        if(username.isEmpty() || password.isEmpty()){
            callback.showError("Un champs n'a pas été remplis")
            return
        }

        repository.connexionWithEmail(username,password).subscribe {
            res -> Log.d("ConnexionEmail", res.toString())
            if(res.success != null){
                //TODO add function to write token in file
                callback.goToDeckListActivity()
            }
            else{

            }
        }


    }

    fun connectionWithFb() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(callback as Activity, permissionNeeds)
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
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
                        callback.goToDeckListActivity()

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
                Log.d("FbError", "Cancel")
            }

            override fun onError(exception: FacebookException) {
                Log.d("FbError", exception.message)
            }
        })
    }

}