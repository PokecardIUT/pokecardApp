package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

class Ability {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("type")
    var type: String? = null

    override fun toString(): String {
        return "Ability{" +
                "name = '" + name + '\''.toString() +
                ",text = '" + text + '\''.toString() +
                ",type = '" + type + '\''.toString() +
                "}"
    }
}