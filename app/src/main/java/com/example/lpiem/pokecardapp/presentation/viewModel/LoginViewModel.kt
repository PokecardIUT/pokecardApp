package com.example.lpiem.pokecardapp.presentation.viewModel

import android.util.Log
import com.example.lpiem.pokecardapp.PokeApplication
import com.example.lpiem.pokecardapp.presentation.ui.view.LoginCallback

class LoginViewModel(var callback:LoginCallback) {

    var repository = PokeApplication.app.repository

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

}