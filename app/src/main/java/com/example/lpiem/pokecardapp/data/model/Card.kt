package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

class Card {

    @SerializedName("types")
    var types: List<String>? = null

    @SerializedName("supertype")
    var supertype: String? = null

    @SerializedName("retreatCost")
    var retreatCost: List<String>? = null

    @SerializedName("set")
    var set: String? = null

    @SerializedName("artist")
    var artist: String? = null

    @SerializedName("setCode")
    var setCode: String? = null

    @SerializedName("hp")
    var hp: String? = null

    @SerializedName("convertedRetreatCost")
    var convertedRetreatCost: Int = 0

    @SerializedName("resistances")
    var resistances: List<Any>? = null

    @SerializedName("number")
    var number: String? = null

    @SerializedName("subtype")
    var subtype: String? = null

    @SerializedName("attacks")
    var attacks: List<AttacksItem>? = null

    @SerializedName("nationalPokedexNumber")
    var nationalPokedexNumber: Int = 0

    @SerializedName("imageUrl")
    var imageUrl: String? = null

    @SerializedName("weaknesses")
    var weaknesses: List<WeaknessesItem>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("imageUrlHiRes")
    var imageUrlHiRes: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("text")
    var text: List<String>? = null

    @SerializedName("ability")
    var ability: Ability? = null

    @SerializedName("rarity")
    var rarity: String? = null

    override fun toString(): String {
        return "Card{" +
                "types = '" + types + '\''.toString() +
                ",supertype = '" + supertype + '\''.toString() +
                ",retreatCost = '" + retreatCost + '\''.toString() +
                ",set = '" + set + '\''.toString() +
                ",artist = '" + artist + '\''.toString() +
                ",setCode = '" + setCode + '\''.toString() +
                ",hp = '" + hp + '\''.toString() +
                ",convertedRetreatCost = '" + convertedRetreatCost + '\''.toString() +
                ",resistances = '" + resistances + '\''.toString() +
                ",number = '" + number + '\''.toString() +
                ",subtype = '" + subtype + '\''.toString() +
                ",attacks = '" + attacks + '\''.toString() +
                ",nationalPokedexNumber = '" + nationalPokedexNumber + '\''.toString() +
                ",imageUrl = '" + imageUrl + '\''.toString() +
                ",weaknesses = '" + weaknesses + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",imageUrlHiRes = '" + imageUrlHiRes + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",text = '" + text + '\''.toString() +
                ",ability = '" + ability + '\''.toString() +
                ",rarity = '" + rarity + '\''.toString() +
                "}"
    }
}