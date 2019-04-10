package com.example.lpiem.pokecardapp.data.model

import com.google.gson.annotations.SerializedName

data class SuccessMessage<T> (
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: List<T>? = null
)