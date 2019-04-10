package com.example.lpiem.pokecardapp.data.model.Trade

import com.google.gson.annotations.SerializedName

data class Trade(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)