package com.quriogamethechance.quriogame.ui.games

import androidx.recyclerview.widget.DiffUtil

class GamesDiffUtil(val oldList:List<Game>, val newList:List<Game>): DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.type == newItem.type &&
                oldItem.image == newItem.image
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return false
    }

}