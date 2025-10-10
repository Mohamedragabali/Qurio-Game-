package com.quriogamethechance.quriogame.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.quriogamethechance.quriogame.ui.lastGames.LastGame

class HomeDiffUtil(val oldList:List<HomeData>, val newList:List<HomeData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

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
        return false
    }

}