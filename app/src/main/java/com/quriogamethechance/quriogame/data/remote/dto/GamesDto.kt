package com.quriogamethechance.quriogame.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GamesDto(
    @SerializedName("response_code")
    val responseCode: Int = 0,
    @SerializedName("results")
    val results: List<Result> = listOf()
)