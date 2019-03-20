package com.example.lpiem.pokecardapp.data.model.Login

import com.google.gson.annotations.SerializedName

data class Login(

		@field:SerializedName("success")
	val success: ResultCode? = null,

		@field:SerializedName("token")
	val token: Token? = null
)