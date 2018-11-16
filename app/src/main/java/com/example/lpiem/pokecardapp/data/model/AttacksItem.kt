package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

data class AttacksItem(

	@field:SerializedName("damage")
	val damage: String? = null,

	@field:SerializedName("cost")
	val cost: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("convertedEnergyCost")
	val convertedEnergyCost: Int? = null
)