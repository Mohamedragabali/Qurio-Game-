package com.quriogamethechance.quriogame.ui.lastGames

import androidx.recyclerview.widget.DiffUtil

class LastGamesDiffUtil(val oldList:List<LastGame>, val newList:List<LastGame>): DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.type == newItem.type &&
                oldItem.data == newItem.data &&
                oldItem.gameTime == newItem.gameTime &&
                oldItem.coinsCount == newItem.coinsCount &&
                oldItem.starCount == newItem.starCount
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return false
    }

}