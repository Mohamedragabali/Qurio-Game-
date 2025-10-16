package com.quriogamethechance.quriogame.ui.buyLife

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface BuyLifeViewInterface : BaseViewInterface {
    fun onBuyAliveSuccess()

    fun onGetLivePriceSuccess(price: Int , coinsCount: Int)
}