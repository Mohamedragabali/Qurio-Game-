package com.quriogamethechance.quriogame.presenter.lastGamePresenter

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.lastGames.LastGameViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LastGamePresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun getLastGames() {
        view.onLoading()
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) { // Switch to IO dispatcher for network/database calls
                        repository.getLastGames()
                    }
                (view as LastGameViewInterface).onGetLastGameSuccess(
                    data
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }
}