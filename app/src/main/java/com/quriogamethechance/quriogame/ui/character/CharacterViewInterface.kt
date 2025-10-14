package com.quriogamethechance.quriogame.ui.character

import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface CharacterViewInterface : BaseViewInterface {
     fun onGetCharactersSuccess(characters: List<Character>)
}