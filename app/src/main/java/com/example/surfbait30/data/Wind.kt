package com.example.surfbait30.data


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("compassDirection")
    val compassDirection: String,
    @SerializedName("speed")
    val speed: Int
)