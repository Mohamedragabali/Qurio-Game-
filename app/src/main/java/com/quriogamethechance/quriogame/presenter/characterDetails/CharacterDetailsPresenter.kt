package com.quriogamethechance.quriogame.presenter.characterDetails

import com.quriogamethechance.quriogame.data.local.charcter.CharacterEntity
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
                        id = character.id,
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

    fun setCharacter(character: Character) {
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val oldSelectedCharacter =repository.getSelectedCharacter().first()
                    repository.updateCharacter(
                        CharacterEntity(
                            id = character.id,
                            name = character.name,
                            description = character.description,
                            age = character.age,
                            price = character.price,
                            isOpen = true,
                            isSelected = true,
                            openImage = character.openImage,
                            closeImage = character.closeImage,
                            characterImage = character.characterImage
                        )
                    )
                    repository.updateCharacter(
                        CharacterEntity(
                            id = oldSelectedCharacter.id,
                            name = oldSelectedCharacter.name,
                            description = oldSelectedCharacter.description,
                            age = oldSelectedCharacter.age,
                            price = oldSelectedCharacter.price,
                            isOpen = oldSelectedCharacter.isOpen,
                            isSelected = false,
                            openImage = oldSelectedCharacter.openImage,
                            closeImage = oldSelectedCharacter.closeImage,
                            characterImage = oldSelectedCharacter.characterImage
                        )
                    )
                }
                (view as CharacterDetailsViewInterface).onSetCharacterSuccess()

            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}