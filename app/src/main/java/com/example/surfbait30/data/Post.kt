package com.example.surfbait30.data


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("swell")
    val swell: Swell,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("wind")
    val wind: Wind
)