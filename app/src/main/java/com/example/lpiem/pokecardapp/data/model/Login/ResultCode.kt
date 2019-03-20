package com.example.lpiem.pokecardapp.data.model.Login

import com.google.gson.annotations.SerializedName

data class ResultCode(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)