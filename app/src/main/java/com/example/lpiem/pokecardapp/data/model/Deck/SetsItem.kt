package com.example.lpiem.pokecardapp.data.model.Deck

import com.google.gson.annotations.SerializedName

data class SetsItem(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("standardLegal")
	val standardLegal: Boolean? = null,

	@field:SerializedName("ptcgoCode")
	val ptcgoCode: String? = null,

	@field:SerializedName("releaseDate")
	val releaseDate: String? = null,

	@field:SerializedName("series")
	val series: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("symbolUrl")
	val symbolUrl: String? = null,

	@field:SerializedName("totalCards")
	val totalCards: Int? = null,

	@field:SerializedName("expandedLegal")
	val expandedLegal: Boolean? = null,

	@field:SerializedName("logoUrl")
	val logoUrl: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)