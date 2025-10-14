package com.quriogamethechance.quriogame.presenter.characterDetails

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.characterDetails.CharacterDetailsViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CharacterDetailsPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job


    fun getCharacter(characterName: String) {
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) { // Switch to IO dispatcher for network/database calls
                        repository.getCharacterByName(characterName)
                    }
                val character = data.first()
                (view as CharacterDetailsViewInterface).onGetCharacterSuccess(
                    Character(
                        name = character.name,
                        description = character.description,
                        age = character.age,
                        price = character.price,
                        isOpen = character.isOpen,
                        isSelected = character.isSelected,
                        openImage = character.openImage,
                        closeImage = character.closeImage,
                        characterImage = character.characterImage
                    )
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}