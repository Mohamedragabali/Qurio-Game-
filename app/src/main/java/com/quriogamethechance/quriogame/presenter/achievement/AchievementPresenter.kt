package com.quriogamethechance.quriogame.presenter.achievement

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.achievement.AchievementViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AchievementPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job


    fun getAchievements(){
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) {
                        repository.getAllAchievements()
                    }
                (view as AchievementViewInterface).onGetAchievementsSuccess(
                    data.map {
                        Achievement(
                            id = it.id,
                            name = it.name,
                            nickname = it.nickname,
                            description = it.description,
                            isOpen = it.isOpen,
                            openImage = it.openImage,
                            closeImage = it.closeImage,
                            howToGetIt = it.howToGetIt
                        )
                    }
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}