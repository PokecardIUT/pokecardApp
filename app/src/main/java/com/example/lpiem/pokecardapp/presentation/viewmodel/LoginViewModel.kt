package com.example.lpiem.pokecardapp.presentation.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.data.model.ErrorMessage
import com.example.lpiem.pokecardapp.data.model.Login.Login
import com.example.lpiem.pokecardapp.data.model.User.User
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    var repository = PokeApplication.getInstance().repository
    var userLiveData = MutableLiveData<User>()
    var isLoggedFbLiveDate = MutableLiveData<Boolean>()
    var error = MutableLiveData<ErrorMessage>()

    fun connexionWithEmail(username: String, password: String){
        if(username.isEmpty() || password.isEmpty()){
            this.error.postValue(ErrorMessage("Un champs n'a pas été remplis"))
            return
        }




        repository.connexionWithEmail(username,password).enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if(response.body()?.success != null){
                    val user = User()
                    user.name = username
                    user.token = response.body()?.token?.token
                    repository.setUser(user)
                    userLiveData.postValue(user)
                } else {
                    error.postValue(ErrorMessage("Erreur d'authentification"))
                }
            }


        })
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

    fun isLoggedFb() { this.isLoggedFbLiveDate.postValue(repository.isLoggedFb()) }

    fun updateSharedPreferences(context: Context, user: User) {
        repository.updateSharedPreferences(context, user)
    }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        return repository.getSharedPreferences(context)
    }

    fun getInfoFb(token: AccessToken) {
        val request = GraphRequest.newMeRequest(
                token
        ) { `object`, response ->
            Log.v("LoginActivity", response.toString())
            try {
                val user = User()
                user.name = `object`.getString("name")
                user.email = `object`.getString("email")
                user.name = user.name!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                this.userLiveData.postValue(user)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "name,email")
        request.parameters = parameters
        request.executeAsync()
    }


    fun getInfoGoogle(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult<ApiException>(ApiException::class.java)
            val user = User()
            user.email = account!!.email
            user.name = account.givenName

            this.userLiveData.postValue(user)

        } catch (e: ApiException) {

            Log.w("mlk", "signInResult:failed code=" + e.statusCode)
        }

    }

    fun getUser(): LiveData<User> = userLiveData

    fun getIsLoggedFb(): LiveData<Boolean> = isLoggedFbLiveDate

    fun getError(): LiveData<ErrorMessage> = error
}