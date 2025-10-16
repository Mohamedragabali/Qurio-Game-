package com.quriogamethechance.quriogame.ui.settings

import com.quriogamethechance.quriogame.presenter.setting.Setting
import com.quriogamethechance.quriogame.ui.base.BaseViewInterface

interface SettingViewInterface : BaseViewInterface {
    fun onGetSettingSuccess(setting: Setting)
    fun onSetSettingSuccess()
}