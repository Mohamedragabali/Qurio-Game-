package com.quriogamethechance.quriogame.ui.games

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentGamesBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import kotlin.math.max


class GamesFragment : BaseFragment<FragmentGamesBinding>(), GameInteraction {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGamesBinding
        get() = FragmentGamesBinding::inflate

    val adapter: GamesAdapter = GamesAdapter(emptyList() , this)
    override fun setup() {
        setHeader()
        setRecycler()
        setAdapter()

    }

    private fun setRecycler() {
        val displayMetrics = requireContext().resources.displayMetrics
        val screenWidthPx = displayMetrics.widthPixels
        val desiredItemWidthPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 170f, displayMetrics
        )
        val spanCount = max(1, (screenWidthPx / desiredItemWidthPx).toInt())
        binding.gamesAdapter.layoutManager = GridLayoutManager(requireContext(),spanCount)
    }

    private fun setAdapter() {
        binding.gamesAdapter.adapter = adapter
        adapter.setData(
            listOf(
                Game(
                    type = "Geography",
                    image = R.drawable.geography,
                    id = 22
                ),
                Game(
                    type = "Science",
                    image = R.drawable.science,
                    id = 30
                ),
                Game(
                    type = "General Knowledge",
                    image = R.drawable.general_knowledge,
                    id = 9
                ),
                Game(
                    type = "Music",
                    image = R.drawable.music,
                    id = 12
                ),
                Game(
                    type = "Film & TV",
                    image = R.drawable.film_tv,
                    id = 11
                ),
                Game(
                    type = "Food & Drink",
                    image = R.drawable.food_drink,
                    id = 17
                ),
                Game(
                    type = "Society & Culture",
                    image = R.drawable.society_culture,
                    id = 24
                ),
                Game(
                    type = "Sport & Leisure",
                    image = R.drawable.sport_leisure,
                    id = 21
                ),
                Game(
                    type = "History",
                    image = R.drawable.history,
                    id = 23
                ),
                Game(
                    type = "Arts & Literature",
                    image = R.drawable.arts_literature,
                    id = 25
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

    override fun onClickPlayNow( id: Int) {
        val action = GamesFragmentDirections.actionGamesFragmentToDifficultyLevelFragment2(
            gameTypeId = id
        )
        binding.root.findNavController().navigate(action)
    }
}