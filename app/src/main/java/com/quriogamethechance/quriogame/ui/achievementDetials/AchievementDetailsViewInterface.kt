package com.quriogamethechance.quriogame.ui.achievementDetials

import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface AchievementDetailsViewInterface : BaseViewInterface {
    fun onGetAchievementSuccess(achievement: Achievement)

}