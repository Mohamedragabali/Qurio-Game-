package com.quriogamethechance.quriogame.presenter.game

import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.game_fragment.GameViewInterface
import com.quriogamethechance.quriogame.ui.game_fragment.Question

class GamePresenter(

) : BasePresenter() {
    fun onGetQuestionGame(){
        view.onLoading()
        (view as GameViewInterface).onGetGameQuestion(
            listOf(
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
        )
        view.onGetDataSuccess()
    }
}