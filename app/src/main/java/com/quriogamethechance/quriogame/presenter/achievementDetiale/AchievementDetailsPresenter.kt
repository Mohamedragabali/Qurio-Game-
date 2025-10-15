package com.quriogamethechance.quriogame.presenter.achievementDetiale

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.achievementDetials.AchievementDetailsViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AchievementDetailsPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job


    fun getAchievement(achievementNickName: String) {
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) {
                        repository.getAchievementsByName(achievementNickName)
                    }
                (view as AchievementDetailsViewInterface).onGetAchievementSuccess(

                    Achievement(
                        id = data.id,
                        name = data.name,
                        nickname = data.nickname,
                        description = data.description,
                        isOpen = data.isOpen,
                        openImage = data.openImage,
                        closeImage = data.closeImage,
                        howToGetIt = data.howToGetIt
                    )

                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}