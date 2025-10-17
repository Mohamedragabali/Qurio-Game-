package com.quriogamethechance.quriogame.presenter.gameResult

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GameResultPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    ) {
        view.onLoading()
        launch {
            try {
                    withContext(Dispatchers.IO) {
                        repository.insertLastGame(
                            typeId,
                            coinsCount,
                            starCount,
                            time,
                            date
                        )
                    }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun setAward(coinsCount: Int){
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val oldCoins  = withContext(Dispatchers.IO) {
                       repository.getCoins()
                    }
                    repository.setCoins(oldCoins + coinsCount)
                }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun setAlive(aliveCount: Int){
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val oldCoins  = withContext(Dispatchers.IO) {
                        repository.getLives()
                    }
                    repository.setLives(oldCoins + aliveCount)
                }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

     fun handleAchievement(
        correctAnswerInRow: Int,
        luckyCorrectAnswer: Int,
        levelType: String,
        starCount: Int
    ){
        launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.handleAchievement(
                        correctAnswerInRow =   correctAnswerInRow,
                        luckyCorrectAnswer = luckyCorrectAnswer,
                        levelType = levelType,
                        starCount = starCount
                    )
                }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }
}