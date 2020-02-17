package com.example.surfbait30.data


import com.google.gson.annotations.SerializedName

data class Swell(
    @SerializedName("maxBreakingHeight")
    val maxBreakingHeight: Int,
    @SerializedName("minBreakingHeight")
    val minBreakingHeight: Int
)