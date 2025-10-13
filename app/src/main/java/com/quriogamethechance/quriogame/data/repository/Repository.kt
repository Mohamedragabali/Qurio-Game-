package com.quriogamethechance.quriogame.data.repository

import com.quriogamethechance.quriogame.data.remote.dto.GamesDto

interface Repository  {
    suspend fun getGameQuestion(gameId : Int , difficulty : String):GamesDto
}