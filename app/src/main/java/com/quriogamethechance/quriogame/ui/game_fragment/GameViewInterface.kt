package com.quriogamethechance.quriogame.ui.game_fragment

import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface GameViewInterface : BaseViewInterface {
    fun onGetGameQuestion(questionsList:List<Question>)
}