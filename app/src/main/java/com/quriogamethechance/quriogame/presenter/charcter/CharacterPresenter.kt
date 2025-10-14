package com.quriogamethechance.quriogame.presenter.charcter

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.character.CharacterViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CharacterPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job


    fun getCharacters(){
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) { // Switch to IO dispatcher for network/database calls
                        repository.getAllCharacters()
                    }
                (view as CharacterViewInterface).onGetCharactersSuccess(
                    data.map {
                        Character(
                            name = it.name,
                            description = it.description,
                            age = it.age,
                            price = it.price,
                            isOpen = it.isOpen,
                            isSelected = it.isSelected,
                            openImage = it.openImage,
                            closeImage = it.closeImage,
                            characterImage =  it.characterImage
                        )
                    }
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

}