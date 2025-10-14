package com.quriogamethechance.quriogame.ui.home

import androidx.recyclerview.widget.DiffUtil

class HomeDiffUtil(val oldList: List<HomeData>, val newList: List<HomeData>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when {
            oldItem is HomeData.UserInformation && newItem is HomeData.UserInformation -> {
                oldItem.characterName == newItem.characterName && oldItem.characterImage == newItem.characterImage
            }

            oldItem is HomeData.Dashboard && newItem is HomeData.Dashboard -> {
                oldItem.livesCount == newItem.livesCount &&
                        oldItem.pointsCount == newItem.pointsCount &&
                        oldItem.awardsCount == newItem.awardsCount
            }

            oldItem is HomeData.TrackingLogin && newItem is HomeData.TrackingLogin ->
                oldItem == newItem

            oldItem is HomeData.Games && newItem is HomeData.Games -> {
                oldItem.games == newItem.games
            }

            oldItem is HomeData.Header && newItem is HomeData.Header -> {
                oldItem.headerType == newItem.headerType
            }

            oldItem is HomeData.LastGames && newItem is HomeData.LastGames -> {
                oldItem.data == newItem.data &&
                        oldItem.coinsCount == newItem.coinsCount &&
                        oldItem.starCount == newItem.starCount &&
                        oldItem.gameTime == newItem.gameTime
            }

            else -> false
        }
    }

}