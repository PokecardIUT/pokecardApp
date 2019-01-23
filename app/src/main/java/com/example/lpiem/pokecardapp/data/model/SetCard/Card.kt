package com.example.lpiem.pokecardapp.data.model.SetCard

data class Card(
	val types: List<String?>? = null,
	val supertype: String? = null,
	val retreatCost: List<String?>? = null,
	val set: String? = null,
	val artist: String? = null,
	val setCode: String? = null,
	val hp: String? = null,
	val convertedRetreatCost: Int? = null,
	val evolvesFrom: String? = null,
	val number: String? = null,
	val subtype: String? = null,
	val attacks: List<AttacksItem?>? = null,
	val nationalPokedexNumber: Int? = null,
	val series: String? = null,
	val imageUrl: String? = null,
	val weaknesses: List<WeaknessesItem?>? = null,
	val name: String? = null,
	val imageUrlHiRes: String? = null,
	val id: String? = null,
	val rarity: String? = null,
	val ability: Ability? = null,
	val text: List<String?>? = null,
	val resistances: List<ResistancesItem?>? = null
)
