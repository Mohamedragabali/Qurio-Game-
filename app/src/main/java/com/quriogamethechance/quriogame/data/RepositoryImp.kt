package com.quriogamethechance.quriogame.data

import com.quriogamethechance.quriogame.ui.game_fragment.Question

class RepositoryImp () : Repository{
    override fun getGameQuestion(): List<Question> = listOf(
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
    )
}