package com.quriogamethechance.quriogame.ui.home.gamesItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.ItemGameBinding
import com.quriogamethechance.quriogame.databinding.ItemGameHomeBinding
import com.quriogamethechance.quriogame.ui.home.HomeData
import com.quriogamethechance.quriogame.ui.home.HomeInteraction

class GamesAdapter(private var games:List<GameItem>, private val interaction : HomeInteraction): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game_home,parent,false)
        return GameViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) {
        val game = games[position]
        holder.binding.apply {
            typeText.text = game.type
            image.setImageResource(game.image)
            buttonPlayNow.setOnClickListener {
                interaction.onClickPlayNow(game.type)
            }
        }
    }

    override fun getItemCount(): Int =games.size


    class GameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val binding = ItemGameHomeBinding.bind(viewItem)
    }
}