package com.quriogamethechance.quriogame.presenter.main

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.main.MainViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun getIsAppOpenBefore() {
        launch {
            try {
                val data =
                    withContext(Dispatchers.IO) {
                        repository.getIsAppOpenBefore()
                    }
                (view as MainViewInterface).onGetAppOpenBefore(
                    data
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }
}