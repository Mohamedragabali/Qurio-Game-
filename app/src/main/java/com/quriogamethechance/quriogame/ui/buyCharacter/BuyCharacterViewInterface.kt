package com.quriogamethechance.quriogame.ui.buyCharacter

import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface BuyCharacterViewInterface : BaseViewInterface {
    fun onGetDataSuccess(character: Character , coinsCount:Int )
    fun onBuyCharacterSuccess()
}