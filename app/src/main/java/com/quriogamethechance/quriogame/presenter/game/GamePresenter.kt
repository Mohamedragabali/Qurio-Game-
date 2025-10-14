package com.quriogamethechance.quriogame.presenter.game

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.game_fragment.GameViewInterface
import com.quriogamethechance.quriogame.ui.game_fragment.Question
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GamePresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main) : BasePresenter() , CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun onGetQuestionGame(gameId : Int , difficulty : String ){
        launch {
            try {
                val data = withContext(Dispatchers.IO) { // Switch to IO dispatcher for network/database calls
                    repository.getGameQuestion(gameId = gameId , difficulty = difficulty)
                }
                (view as GameViewInterface).onGetGameQuestion(
                    data.results.map {
                        Question(
                            question = it.question,
                            options = it.incorrectAnswers + it.correctAnswer,
                            correctAnswer = it.correctAnswer
                        )
                    }
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getLive(){
        launch {
            try {
                val lives =
                    withContext(Dispatchers.IO) {
                        repository.getLives()
                    }
                (view as GameViewInterface).onGetLiveSuccess(
                    liveCount = lives
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}