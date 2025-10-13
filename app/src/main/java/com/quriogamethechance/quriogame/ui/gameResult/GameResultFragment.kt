package com.quriogamethechance.quriogame.ui.gameResult

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentGameResultBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment

class GameResultFragment : BaseFragment<FragmentGameResultBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameResultBinding
        get() = FragmentGameResultBinding::inflate

    override fun setup() {

    }

}