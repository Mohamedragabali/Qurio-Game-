package com.quriogamethechance.quriogame.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.ItemDashboardBinding
import com.quriogamethechance.quriogame.databinding.ItemGamesBinding
import com.quriogamethechance.quriogame.databinding.ItemHeaderHomeBinding
import com.quriogamethechance.quriogame.databinding.ItemLastGameBinding
import com.quriogamethechance.quriogame.databinding.ItemTrackingLoginBinding
import com.quriogamethechance.quriogame.databinding.ItemUserInformationBinding

class HomeAdapter(private var list: List<HomeData>, private val interaction: HomeInteraction) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return when (viewType) {
            HomeItemType.USER_INFORMATION.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_information, parent, false)
                UserInformationViewHolder(view)
            }

            HomeItemType.DASHBOARD.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_dashboard, parent, false)
                DashboardViewHolder(view)
            }

            HomeItemType.TRACKING_LOGIN.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_tracking_login, parent, false)
                TrackingLoginViewHolder(view)
            }

            HomeItemType.GAMES.ordinal -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_games, parent, false)
                GamesViewHolder(view)
            }

            HomeItemType.LAST_GAMES.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_last_game, parent, false)
                LastGamesViewHolder(view)
            }

            HomeItemType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_home, parent, false)
                HeaderViewHolder(view)
            }

            else -> throw IllegalArgumentException("Unknown viewType: $viewType")

        }
    }

    fun setData(newList: List<HomeData>) {
        val diffResult = DiffUtil.calculateDiff(HomeDiffUtil(list,newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        val data = list[position]
        when (holder) {
            is UserInformationViewHolder -> {
                data as HomeData.UserInformation
                holder.binding.apply {
                    characterName.text = data.characterName
                    characterImage.setImageResource(data.characterImage)
                    characterImage.setOnClickListener {

                        interaction.onClickCharacter()

                    }
                }
            }

            is DashboardViewHolder -> {

            }

            is TrackingLoginViewHolder -> {

            }

            is GamesViewHolder -> {

            }

            is LastGamesViewHolder -> {
                data as HomeData.LastGames
                holder.binding.apply {
                    dataText.text=data.data
                    gameTypeText.text=data.lastGameType
                    coinsCountText.text = data.coinsCount.toString()
                    starCountText.text = data.starCount.toString()
                    gameTimeText.text = data.gameTime
                }
            }

            is HeaderViewHolder -> {
                data as HomeData.Header
                holder.binding.apply {
                    headerText.text = data.headerTitle
                    nextButton.setOnClickListener {
                        interaction.onClickHeader(data.headerType)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size


    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)


    class UserInformationViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemUserInformationBinding.bind(viewItem)
    }

    class DashboardViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemDashboardBinding.bind(viewItem)
    }

    class TrackingLoginViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemTrackingLoginBinding.bind(viewItem)
    }

    class GamesViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemGamesBinding.bind(viewItem)
    }

    class LastGamesViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemLastGameBinding.bind(viewItem)
    }

    class HeaderViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemHeaderHomeBinding.bind(viewItem)
    }

}