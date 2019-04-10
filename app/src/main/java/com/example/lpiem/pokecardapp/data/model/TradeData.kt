package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

data class TradeData (

    @SerializedName("username")
    var username: String? = null,
    @SerializedName("message")
    var message: String? = null

)