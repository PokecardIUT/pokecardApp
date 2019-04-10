package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class CardsUser(

	@field:SerializedName("imageUrlHiRes")
	var imageUrlHiRes: String? = null,

	@field:SerializedName("imageUrl")
	var imageUrl: String? = null,

	@field:SerializedName("id")
	var id: String? = null
)