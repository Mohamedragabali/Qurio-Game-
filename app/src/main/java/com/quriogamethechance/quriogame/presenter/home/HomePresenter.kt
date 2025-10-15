package com.quriogamethechance.quriogame.presenter.home

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.home.HomeViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomePresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun getLastGames() {
        view.onLoading()
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) {
                        repository.getLastGames()
                    }
                (view as HomeViewInterface).onGetLastGameSuccess(
                    data
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getDashboardData(){
        launch {
            try {
                val lives =
                    withContext(Dispatchers.IO) {
                        repository.getLives()
                    }
                val coins = withContext(Dispatchers.IO) {
                    repository.getCoins()
                }
                val awardCount = withContext(Dispatchers.IO) {
                    repository.getOpenAchievements()
                }
                (view as HomeViewInterface).onGetDashboardSuccess(
                    livesCount = lives,
                    coinsCount = coins,
                    awardsCount = awardCount.size
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getCharacterInformation() {
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) {
                        repository.getSelectedCharacter()
                    }
                val character = data.first()
                (view as HomeViewInterface).onGetCharacterInformationSuccess(
                    characterName = character.name,
                    characterImage = character.openImage
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getTrackingLogin(
        weekDays:List<String>
    ) {
        launch {
            try {
                val trackingResult = withContext(Dispatchers.IO) {
                    repository.getTrackingResult(weekDays)
                }
                (view as HomeViewInterface).onGetTrackingLoginSuccess(trackingResult)
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }
}