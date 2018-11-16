package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

class WeaknessesItem {

    @SerializedName("type")
    var type: String? = null

    @SerializedName("value")
    var value: String? = null

    override fun toString(): String {
        return "WeaknessesItem{" +
                "type = '" + type + '\''.toString() +
                ",value = '" + value + '\''.toString() +
                "}"
    }
}