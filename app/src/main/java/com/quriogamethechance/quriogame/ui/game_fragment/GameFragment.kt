package com.quriogamethechance.quriogame.ui.game_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentGameBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment


class GameFragment : BaseFragment<FragmentGameBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameBinding
        get() =  FragmentGameBinding::inflate

    override fun setup() {


    }

}