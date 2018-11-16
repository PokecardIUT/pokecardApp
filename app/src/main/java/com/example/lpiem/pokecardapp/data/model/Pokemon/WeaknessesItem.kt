package com.example.lpiem.pokecardapp.data.model.Pokemon

import com.google.gson.annotations.SerializedName

data class WeaknessesItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)