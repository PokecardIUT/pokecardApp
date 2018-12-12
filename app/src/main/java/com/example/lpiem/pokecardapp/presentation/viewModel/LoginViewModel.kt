package com.example.lpiem.pokecardapp.presentation.viewModel

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback

class LoginViewModel(var callback:LoginCallback) {

    var repository = PokeApplication.app.repository
    lateinit var sharedPreferences: SharedPreferences

    fun initSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(callback.getContext())
    }

    fun connexionWithEmail(username: String, password: String){
        if(username.isEmpty() || password.isEmpty()){
            callback.showError("Un champs n'a pas été remplis")
            return
        }

        repository.connexionWithEmail(username,password).subscribe {
            res -> Log.d("ConnexionEmail", res.toString())
            if(res.success != null){
                var token = res.token?.token

                var editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("token", token)
                editor.commit()
                Log.d("tokenEmail", sharedPreferences.getString("token", "not defined"))
                //TODO add function to write token in file
                callback.goToDeckListActivity()
            }
            else{

            }
        }


    }

}