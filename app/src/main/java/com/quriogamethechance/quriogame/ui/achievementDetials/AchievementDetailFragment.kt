package com.quriogamethechance.quriogame.ui.achievementDetials

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentAchievementDetialBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class AchievementDetailFragment : BaseDialogFragment<FragmentAchievementDetialBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAchievementDetialBinding
        get() = FragmentAchievementDetialBinding::inflate

    override fun setup() {

    }
}