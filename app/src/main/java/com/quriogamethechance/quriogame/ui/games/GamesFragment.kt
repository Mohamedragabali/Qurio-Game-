package com.quriogamethechance.quriogame.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentGamesBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment


class GamesFragment : BaseFragment<FragmentGamesBinding>(), GameInteraction {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGamesBinding
        get() = FragmentGamesBinding::inflate

    val adapter: GamesAdapter = GamesAdapter(emptyList() , this)
    override fun setup() {
        setHeader()
        setAdapter()

    }

    private fun setAdapter() {
        binding.gamesAdapter.adapter = adapter
        adapter.setData(
            listOf(
                Game(
                    type = "Geography",
                    image = R.drawable.geography,
                ),
                Game(
                    type = "Science",
                    image = R.drawable.science,
                ),
                Game(
                    type = "General Knowledge",
                    image = R.drawable.general_knowledge,
                ),
                Game(
                    type = "Music",
                    image = R.drawable.music,
                ),
                Game(
                    type = "Film & TV",
                    image = R.drawable.film_tv,
                ),
                Game(
                    type = "Food & Drink",
                    image = R.drawable.food_drink,
                ),
                Game(
                    type = "Society & Culture",
                    image = R.drawable.society_culture,
                ),
                Game(
                    type = "History",
                    image = R.drawable.history,
                ),
                Game(
                    type = "Arts & Literature",
                    image = R.drawable.arts_literature,
                ),
            )
        )
    }

    private fun setHeader() {
        binding.header.headerText.text = getString(R.string.games)
        binding.header.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onClickPlayNow( type: String) {
        val action = GamesFragmentDirections.actionGamesFragmentToDifficultyLevelFragment2(
            gameType = type
        )
        binding.root.findNavController().navigate(action)
    }
}