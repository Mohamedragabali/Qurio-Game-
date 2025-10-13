package com.quriogamethechance.quriogame.ui.home

interface HomeInteraction {
    fun onClickCharacter()
    fun onClickSitting()

    fun onClickHeader(homeHeaderType: HomeHeaderType)
    fun onClickPlayNow(id:Int)
    fun onClickAddLive(pointsCount:Long)
    fun onClickShowAward()
}