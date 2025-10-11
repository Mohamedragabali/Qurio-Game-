package com.quriogamethechance.quriogame.ui.game_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.quriogamethechance.quriogame.databinding.FragmentGameBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment


class GameFragment : BaseFragment<FragmentGameBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameBinding
        get() =  FragmentGameBinding::inflate

    override fun setup() {
//        val args = GameFragmentArgs.fromBundle(requireArguments())
//        val difficulty = args.gameDifficultyLevel
//        val gameType = args.gameType
//        Toast.makeText(requireContext() , "$difficulty $gameType" , Toast.LENGTH_SHORT).show()
    }

}