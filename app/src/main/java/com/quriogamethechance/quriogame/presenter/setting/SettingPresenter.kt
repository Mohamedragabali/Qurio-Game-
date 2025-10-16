package com.quriogamethechance.quriogame.presenter.setting

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.settings.SettingViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SettingPresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : BasePresenter(), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job


    fun setSetting(soundDegree:Float,musicDegree:Float) {
        view.onLoading()
        launch {
            try {
                    withContext(Dispatchers.IO) {
                        repository.setSetting(soundDegree,musicDegree)
                    }
                (view as SettingViewInterface).onSetSettingSuccess()
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getSetting(){
        launch {
            try {
                    val setting = withContext(Dispatchers.IO) {
                        repository.getSetting()
                    }
                (view as SettingViewInterface).onGetSettingSuccess(setting)
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }



}