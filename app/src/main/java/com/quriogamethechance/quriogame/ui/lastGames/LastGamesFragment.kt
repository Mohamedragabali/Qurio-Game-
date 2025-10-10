package com.quriogamethechance.quriogame.ui.lastGames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentLastGamesBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment


class LastGamesFragment : BaseFragment<FragmentLastGamesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLastGamesBinding
        get() =  FragmentLastGamesBinding::inflate

    val  adapter : LastGamesAdapter = LastGamesAdapter(emptyList())
    override fun setup() {
        setHeader()
        setAdapter()
    }

    private fun setAdapter() {
        binding.lastGames.adapter = adapter
        adapter.setData(
            listOf(
                LastGame(
                    data = "1-5-2020",
                    type = "Video Games",
                    coinsCount = 12,
                    starCount = 0,
                    gameTime = "56sec"
                ),
                LastGame(
                    data = "1-5-2020",
                    type = "Video Games",
                    coinsCount = 12,
                    starCount = 0,
                    gameTime = "56sec"
                ),
                LastGame(
                    data = "1-5-2020",
                    type = "Video Games",
                    coinsCount = 12,
                    starCount = 0,
                    gameTime = "56sec"
                ),
                LastGame(
                    data = "1-5-2020",
                    type = "Video Games",
                    coinsCount = 12,
                    starCount = 0,
                    gameTime = "56sec"
                ),
                LastGame(
                    data = "1-5-2020",
                    type = "Video Games",
                    coinsCount = 12,
                    starCount = 0,
                    gameTime = "56sec"
                ),

                )
        )
    }

    private fun setHeader() {
        binding.header.headerText.text = getString(R.string.last_games)
        binding.header.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}