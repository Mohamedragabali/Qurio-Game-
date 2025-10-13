package com.quriogamethechance.quriogame.data.repository

import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import com.quriogamethechance.quriogame.ui.lastGames.LastGame

interface Repository {
    suspend fun getGameQuestion(gameId: Int, difficulty: String): GamesDto

    suspend fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    )

    suspend fun getLastGames(): List<LastGame>
}