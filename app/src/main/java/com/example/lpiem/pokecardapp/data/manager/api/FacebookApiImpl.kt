package com.example.lpiem.pokecardapp.data.manager.api

import com.facebook.AccessToken

class FacebookApiImpl: FacebookApi {
    override fun isLogged(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

}