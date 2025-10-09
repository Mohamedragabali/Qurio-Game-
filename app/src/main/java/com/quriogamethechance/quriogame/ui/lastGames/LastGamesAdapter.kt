package com.quriogamethechance.quriogame.ui.lastGames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.ItemLastGameBinding

class LastGamesAdapter(private var games:List<Game>): RecyclerView.Adapter<LastGamesAdapter.LastGameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_last_game,parent,false)
        return LastGameViewHolder(view)

    }

    fun setData(newGames : List<Game>){
        val diffResult = DiffUtil.calculateDiff(LastGamesDiffUtil(games,newGames))
        games = newGames
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onBindViewHolder(
        holder: LastGameViewHolder,
        position: Int
    ) {
        val game = games[position]
        holder.binding.apply {
            gameTypeText.text = game.type
            gameTimeText.text = game.gameTime
            starCountText.text = game.starCount.toString()
            coinsCountText.text = game.coinsCount.toString()
            dataText.text = game.data
        }
    }

    override fun getItemCount(): Int =games.size


    class LastGameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val binding = ItemLastGameBinding.bind(viewItem)
    }
}