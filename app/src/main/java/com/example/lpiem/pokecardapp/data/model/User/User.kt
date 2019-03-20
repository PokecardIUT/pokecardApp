package com.example.lpiem.pokecardapp.data.model.User

import com.google.gson.annotations.SerializedName

data class User(

		@field:SerializedName("password")
		val password: String? = null,

		@field:SerializedName("cards")
		val cards: List<CardsUser>? = null,

		@field:SerializedName("sets")
		val sets: List<SetsUser>? = null,

//		@field:SerializedName("__v")
//	val V: Int? = null,
//
//		@field:SerializedName("_id")
//	val id: String? = null,

		@field:SerializedName("username")
		var username: String? = null,

		var token: String? = null,

		@field:SerializedName("level")
		var level: String? = null,

		@field:SerializedName("nbWin")
		var nbWin: Number? = null
)