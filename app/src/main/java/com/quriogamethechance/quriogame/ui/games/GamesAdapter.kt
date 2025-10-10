package com.quriogamethechance.quriogame.ui.games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.ItemGameBinding

class GamesAdapter(private var games:List<Game> , private val interaction : GameInteraction): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game,parent,false)
        return GameViewHolder(view)

    }

    fun setData(newGames : List<Game>){
        val diffResult = DiffUtil.calculateDiff(GamesDiffUtil(games, newGames))
        games = newGames
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) {
        val game = games[position]
        holder.binding.apply {
            typeText.text = game.type
            image.setImageResource(game.image)
            playNowButton.setOnClickListener {
                interaction.onClickPlayNow(game.type)
            }
        }
    }

    override fun getItemCount(): Int =games.size


    class GameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val binding = ItemGameBinding.bind(viewItem)
    }
}