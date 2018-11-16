package com.example.lpiem.pokecardapp.data.model.Pokemon

import com.example.lpiem.pokecardapp.data.model.Pokemon.Card
import com.google.gson.annotations.SerializedName

data class Pokemon(

	@field:SerializedName("card")
	val card: Card? = null
)