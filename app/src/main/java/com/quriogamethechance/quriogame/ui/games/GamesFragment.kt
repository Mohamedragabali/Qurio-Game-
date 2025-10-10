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