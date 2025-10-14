package com.quriogamethechance.quriogame.ui.buyLife

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface BuyLifeViewInterface : BaseViewInterface {
    fun onGetCoinsSuccess(coinsCount : Int )
    fun onBuyAliveSuccess()
}