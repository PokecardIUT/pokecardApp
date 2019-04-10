package com.example.lpiem.pokecardapp.data.model.SetCard

import com.google.gson.annotations.SerializedName

data class Ability(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)