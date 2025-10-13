package com.quriogamethechance.quriogame.ui.lastGames

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.ItemLastGameBinding

class LastGamesAdapter(private var lastGames:List<LastGame>,private val context: Context): RecyclerView.Adapter<LastGamesAdapter.LastGameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_last_game,parent,false)
        return LastGameViewHolder(view)

    }

    fun setData(newLastGames : List<LastGame>){
        val diffResult = DiffUtil.calculateDiff(LastGamesDiffUtil(lastGames,newLastGames))
        lastGames = newLastGames
        diffResult.dispatchUpdatesTo(this)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(
        holder: LastGameViewHolder,
        position: Int
    ) {
        val game = lastGames[position]
        holder.binding.apply {
            gameTypeText.text = game.type
            gameTimeText.text = game.gameTime
            starCountText.text = game.starCount.toString()
            coinsCountText.text = game.coinsCount.toString()
            if(game.coinsCount < 0 ) {
                coinsCountText.setTextColor(context.getColor(R.color.red))

            }else{
                coinsCountText.setTextColor(context.getColor(R.color.shade_primary))
            }
            dataText.text = game.data
        }
    }

    override fun getItemCount(): Int =lastGames.size


    class LastGameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val binding = ItemLastGameBinding.bind(viewItem)
    }
}