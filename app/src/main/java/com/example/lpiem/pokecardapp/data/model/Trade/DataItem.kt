package com.example.lpiem.pokecardapp.data.model.Trade

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)