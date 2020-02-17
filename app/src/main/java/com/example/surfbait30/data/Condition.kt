package com.example.surfbait30.data


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("temperature")
    val temperature: Int
)