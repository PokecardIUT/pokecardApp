package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name") var name:String? = null,
    @SerializedName("email") var email:String? =null,
    @SerializedName("level") var level:Int? = null,
    @SerializedName("nbWin") var nbWin:Int? = null,
    @SerializedName("token") var token:String? = null
)