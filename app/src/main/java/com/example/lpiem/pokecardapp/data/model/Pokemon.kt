package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(

	@field:SerializedName("card")
	val card: Card? = null
)