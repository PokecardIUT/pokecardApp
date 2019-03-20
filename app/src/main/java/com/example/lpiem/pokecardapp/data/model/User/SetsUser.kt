package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class SetsUser(

		@field:SerializedName("cards")
	val cards: List<CardsUser?>? = null,

		@field:SerializedName("description")
	val description: String? = null,

		@field:SerializedName("title")
	val title: String? = null
)