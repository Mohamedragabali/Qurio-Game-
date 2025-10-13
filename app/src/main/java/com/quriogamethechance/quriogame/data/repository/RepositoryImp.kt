package com.quriogamethechance.quriogame.data.repository

import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import jakarta.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: GameApiService
): Repository {
    private val gameQuestionAmount = 12
    private val gameType = "multiple"
    override suspend fun getGameQuestion(
        gameId : Int , difficulty : String
    ): GamesDto= apiService.getGames(
        amount = gameQuestionAmount,
        gameId = gameId,
        gameDifficulty = difficulty,
        gameType = gameType
    )


        /*listOf(
        Question(
            question = "How many US states start with the letter K?",
            correctAnswer = "Two",
            options = listOf("One", "Two", "Three", "None"),
        ),
        Question(
            question = "What state is the largest state of the United States of America?",
            correctAnswer = "Alaska",
            options = listOf("California", "Alaska", "Texas", "Washington"),
        ),
        Question(
            question = "How many US states start with the letter K?",
            correctAnswer = "Two",
            options = listOf("One", "Two", "Three", "None"),
        ),
        Question(
            question = "What state is the largest state of the United States of America?",
            correctAnswer = "Alaska",
            options = listOf("California", "Alaska", "Texas", "Washington"),
        ),
    )*/
}