package com.quriogamethechance.quriogame.ui.characterDetails

import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface CharacterDetailsViewInterface : BaseViewInterface {
     fun onGetCharacterSuccess(characters: Character)
     fun onSetCharacterSuccess()
}