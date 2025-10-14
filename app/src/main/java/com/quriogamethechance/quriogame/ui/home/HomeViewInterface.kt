package com.quriogamethechance.quriogame.ui.home

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface
import com.quriogamethechance.quriogame.ui.lastGames.LastGame

interface HomeViewInterface : BaseViewInterface {
    fun onGetLastGameSuccess(questionsList:List<LastGame>)

    fun onGetDashboardSuccess(livesCount: Int , coinsCount: Int , awardsCount: Int)

}