package com.quriogamethechance.quriogame.ui.home

import com.quriogamethechance.quriogame.ui.home.gamesItem.GameItem


sealed class HomeData(val type: HomeItemType) {
    data class UserInformation(val characterName: String, val characterImage: Int) :
        HomeData(HomeItemType.USER_INFORMATION)

    data class Dashboard(val livesCount: Int, val pointsCount: Long, val awardsCount: Int) :
        HomeData(HomeItemType.DASHBOARD)

    data class TrackingLogin(val days: List<Day>) : HomeData(HomeItemType.TRACKING_LOGIN)

    data class Games(val games:List<GameItem>) : HomeData(HomeItemType.GAMES)

    data class Header (val headerTitle: String,val headerType: HomeHeaderType) : HomeData(HomeItemType.HEADER)

    data class LastGames(
        val data: String,
        val lastGameType: String,
        val coinsCount: Long,
        val starCount: Long,
        val gameTime: String
    ): HomeData(HomeItemType.LAST_GAMES)
}