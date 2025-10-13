package com.quriogamethechance.quriogame.ui.lastGames

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface LastGameViewInterface : BaseViewInterface {
    fun onGetLastGameSuccess(questionsList:List<LastGame>)
}