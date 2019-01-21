package com.example.lpiem.pokecardapp.data.model.Deck

import com.google.gson.annotations.SerializedName

data class Deck(

	@field:SerializedName("sets")
	val sets: List<SetsItem>? = null
)