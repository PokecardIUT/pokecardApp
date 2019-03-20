package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class CardsUser(

	@field:SerializedName("imageUrlHiRes")
	val imageUrlHiRes: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)