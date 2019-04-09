package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class SetsUser(

		@field:SerializedName("cards")
		var cards: List<CardsUser>? = null,

		@field:SerializedName("description")
		var description: String? = null,

		@field:SerializedName("title")
		var title: String? = null
)