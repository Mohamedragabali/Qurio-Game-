package com.quriogamethechance.quriogame.presenter.game

import com.quriogamethechance.quriogame.data.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.game_fragment.GameViewInterface
import jakarta.inject.Inject

class GamePresenter @Inject constructor(
    private val repository: Repository
) : BasePresenter() {
    fun onGetQuestionGame(){
        view.onLoading()

        (view as GameViewInterface).onGetGameQuestion(
            repository.getGameQuestion()
        )
        view.onGetDataSuccess()
    }
}