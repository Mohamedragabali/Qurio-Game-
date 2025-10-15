package com.quriogamethechance.quriogame.ui.achievement

import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface AchievementViewInterface : BaseViewInterface {
    fun onGetAchievementsSuccess(achievementsList: List<Achievement>)

}