package com.quriogamethechance.quriogame.ui.gameResult

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface GameResultViewInterface : BaseViewInterface {
    fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    )
}