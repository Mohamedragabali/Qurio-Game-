package com.quriogamethechance.quriogame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentHomeBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import com.quriogamethechance.quriogame.ui.home.gamesItem.GameItem

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteraction {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    val adapter = HomeAdapter(emptyList(), this)
    override fun setup() {
        binding.homeRecyclerView.adapter = adapter

        adapter.setData(
            listOf(
                HomeData.UserInformation(
                    characterName = "Hallo ",
                    characterImage = R.drawable.rika
                ),

                HomeData.Dashboard(
                    livesCount = 5,
                    pointsCount = 5000,
                    awardsCount = 4
                ),
                HomeData.TrackingLogin(
                    listOf(
                        Day(
                            "S",
                            false
                        ),
                        Day(
                            "M",
                            true
                        ),
                        Day(
                            "T",
                            false
                        ),
                        Day(
                            "W",
                            false
                        ),
                        Day(
                            "Th",
                            false
                        ),
                        Day(
                            "F",
                            false
                        ),
                        Day(
                            "S",
                            false
                        )
                    )
                ),
                HomeData.Header(
                    headerTitle = "Games",
                    headerType = HomeHeaderType.GAMES
                ),
                HomeData.Games(
                    listOf(
                        GameItem(
                            type = "Geography",
                            image = R.drawable.geography,
                            id = 22
                        ),
                        GameItem(
                            type = "Science",
                            image = R.drawable.science,
                            id = 30
                        ),
                        GameItem(
                            type = "General Knowledge",
                            image = R.drawable.general_knowledge,
                            id = 9
                        ),
                        GameItem(
                            type = "Music",
                            image = R.drawable.music,
                            id = 12
                        ),
                        GameItem(
                            type = "Film & TV",
                            image = R.drawable.film_tv,
                            id = 11
                        ),
                        GameItem(
                            type = "Food & Drink",
                            image = R.drawable.food_drink,
                            id = 17
                        ),
                        GameItem(
                            type = "Society & Culture",
                            image = R.drawable.society_culture,
                            id = 24
                        ),
                        GameItem(
                            type = "History",
                            image = R.drawable.history,
                            id = 23
                        ),
                        GameItem(
                            type = "Arts & Literature",
                            image = R.drawable.arts_literature,
                            id = 25
                        ),
                    )
                ),
                HomeData.Header(
                    headerTitle = "Last Games",
                    headerType = HomeHeaderType.LAST_GAMES
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
            )
        )
    }

    override fun onClickCharacter() {
        val action = HomeFragmentDirections.actionHomeFragmentToCharacterFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickSitting() {
        val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickHeader(homeHeaderType: HomeHeaderType) {
        val action = HomeFragmentDirections
        when (homeHeaderType) {
            HomeHeaderType.GAMES -> binding.root.findNavController()
                .navigate(action.actionHomeFragmentToGamesFragment())

            HomeHeaderType.LAST_GAMES -> binding.root.findNavController()
                .navigate(action.actionHomeFragmentToLastGamesFragment())
        }
    }

    override fun onClickPlayNow(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDifficultyLevelFragment(id)
        binding.root.findNavController().navigate(action)
    }

    override fun onClickAddLive(pointsCount: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToBuyLifeFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickShowAward() {
        val action = HomeFragmentDirections.actionHomeFragmentToAchievementFragment()
        binding.root.findNavController().navigate(action)
    }

}