package com.example.lpiem.pokecardapp.data.manager.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.lpiem.pokecardapp.data.model.User.User
import com.google.gson.Gson

class SharedPreferencesManager {
    lateinit var sharedPreferences: SharedPreferences

    fun updateSharedPreferences(context: Context, user: User) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("user", Gson().toJson(user::class.java))
        Log.d("token", sharedPreferences.getString("token", "token"))
        editor.commit()
    }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}