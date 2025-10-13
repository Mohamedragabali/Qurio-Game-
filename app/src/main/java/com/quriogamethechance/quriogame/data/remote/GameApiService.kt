package com.quriogamethechance.quriogame.data.remote

import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {

    @GET("/api.php")
    suspend fun getGames(
        @Query("amount")amount : Int,
        @Query("category")gameId : Int,
        @Query("difficulty")gameDifficulty : String,
        @Query("type")gameType : String): GamesDto
}

