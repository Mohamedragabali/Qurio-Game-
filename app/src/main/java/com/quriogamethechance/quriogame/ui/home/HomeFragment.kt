package com.quriogamethechance.quriogame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentHomeBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteraction {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() =  FragmentHomeBinding::inflate
    val adapter = HomeAdapter(emptyList() , this )
    override fun setup() {
        binding.homeRecyclerView.adapter = adapter

        adapter.setData(
            listOf(
                HomeData.UserInformation(
                    characterName = "Hallo " ,
                    characterImage = R.drawable.rika
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400 ,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400 ,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400 ,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400 ,
                    starCount = 7,
                    gameTime = "44Sec"
                ),
                HomeData.LastGames(
                    data = "0-9-2020",
                    lastGameType = "Gamming",
                    coinsCount = 400 ,
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

}