package com.quriogamethechance.quriogame.presenter.onboarding

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class OnboardingPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job
    private val startLivesCount = 5
    private val startCoinsCount = 500


    fun setAppOpen() {
        view.onLoading()
        launch {
            try {
                    withContext(Dispatchers.IO) {
                        repository.setAppOpen()
                    }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun setLives(){
        launch {
            try {
                    withContext(Dispatchers.IO) {
                        repository.setLives(startLivesCount)
                    }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun setCoins(){
        launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.setCoins(startCoinsCount)
                }
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}