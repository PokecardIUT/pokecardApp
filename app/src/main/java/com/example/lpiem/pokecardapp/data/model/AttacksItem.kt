package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

class AttacksItem {

    @SerializedName("damage")
    var damage: String? = null

    @SerializedName("cost")
    var cost: List<String>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("convertedEnergyCost")
    var convertedEnergyCost: Int = 0

    override fun toString(): String {
        return "AttacksItem{" +
                "damage = '" + damage + '\''.toString() +
                ",cost = '" + cost + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",text = '" + text + '\''.toString() +
                ",convertedEnergyCost = '" + convertedEnergyCost + '\''.toString() +
                "}"
    }
}