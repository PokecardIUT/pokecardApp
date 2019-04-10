package com.example.lpiem.pokecardapp.data.model.SetCard

import com.google.gson.annotations.SerializedName

data class Card(

	@field:SerializedName("card")
	val card: Card? = null,

	@field:SerializedName("types")
	val types: List<String?>? = null,

	@field:SerializedName("supertype")
	val supertype: String? = null,

	@field:SerializedName("retreatCost")
	val retreatCost: List<String?>? = null,

	@field:SerializedName("set")
	val set: String? = null,

	@field:SerializedName("artist")
	val artist: String? = null,

	@field:SerializedName("setCode")
	val setCode: String? = null,

	@field:SerializedName("hp")
	val hp: String? = null,

	@field:SerializedName("convertedRetreatCost")
	val convertedRetreatCost: Int? = null,

	@field:SerializedName("evolvesFrom")
	val evolvesFrom: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("subtype")
	val subtype: String? = null,

	@field:SerializedName("attacks")
	val attacks: List<AttacksItem?>? = null,

	@field:SerializedName("nationalPokedexNumber")
	val nationalPokedexNumber: Int? = null,

	@field:SerializedName("series")
	val series: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("weaknesses")
	val weaknesses: List<WeaknessesItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("imageUrlHiRes")
	val imageUrlHiRes: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ability")
	val ability: Ability? = null,

	@field:SerializedName("rarity")
	val rarity: String? = null
)