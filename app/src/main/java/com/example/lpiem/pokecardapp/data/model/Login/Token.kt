package com.example.lpiem.pokecardapp.data.model.Login

import com.google.gson.annotations.SerializedName

data class Token(

	@field:SerializedName("expires")
	val expires: Long? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)