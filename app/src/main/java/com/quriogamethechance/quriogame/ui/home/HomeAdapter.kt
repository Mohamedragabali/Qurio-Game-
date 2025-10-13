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
import com.quriogamethechance.quriogame.databinding.TrackingDayItemBinding
import com.quriogamethechance.quriogame.ui.home.gamesItem.GameItem
import com.quriogamethechance.quriogame.ui.home.gamesItem.GamesAdapter

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
        val diffResult = DiffUtil.calculateDiff(HomeDiffUtil(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: HomeData) {
        val newList = list.toMutableList()
        newList.add(item)
        val diffResult = DiffUtil.calculateDiff(HomeDiffUtil(list, newList))
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
                    sittingIcon.setOnClickListener {
                        interaction.onClickSitting()
                    }
                }
            }

            is DashboardViewHolder -> {
                data as HomeData.Dashboard
                holder.binding.apply {
                    addLiveButton.root.setOnClickListener {
                        interaction.onClickAddLive(data.pointsCount)
                    }
                    showAwardButton.root.setOnClickListener {
                        interaction.onClickShowAward()
                    }
                    crown.visibility =
                        if (data.pointsCount >= 10000) View.VISIBLE else View.INVISIBLE
                    coinsCount.text = data.pointsCount.toString()
                    awardCount.text = data.awardsCount.toString()
                    livesCount.text = data.livesCount.toString()
                }
            }

            is TrackingLoginViewHolder -> {
                data as HomeData.TrackingLogin
                holder.binding.apply {
                    val daysTracking =
                        listOf(sunDay, monDay, tueDay, wedDay, thuDay, friDay, satDay)
                    val doneDayCount = data.days.filter { it.isDone }.size
                    trackingLoginText.text = if (doneDayCount == 0) "0 day streak, start make a series"
                        else "$doneDayCount day streak, make a big series"
                    supportTrackingText.text = if (doneDayCount == 0) "Every day count!"
                        else "KEEP IT UP!"
                    data.days.forEachIndexed { dayIndex, day ->
                        manageCircleDay(daysTracking[dayIndex], day)
                    }
                }
            }

            is GamesViewHolder -> {
                val data: List<GameItem> = (data as HomeData.Games).games
                holder.binding.apply {
                    gamesRecyclerView.adapter = GamesAdapter(data, interaction)
                }
                val overlapOffset = 10

                holder.binding.gamesRecyclerView.apply {
                    setPadding(overlapOffset, 0, overlapOffset, 0)
                }

                holder.binding.gamesRecyclerView.addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val center = recyclerView.width / 2

                        var rightChild: View? = null
                        for (i in 0 until recyclerView.childCount) {
                            val child = recyclerView.getChildAt(i) ?: continue
                            if (i + 1 < recyclerView.childCount) {
                                rightChild = recyclerView.getChildAt(i + 1)
                            }
                            val childCenter = (child.left + child.right) / 2
                            val distanceFromCenter = (center - childCenter).toFloat()
                            val ratio = kotlin.math.abs(distanceFromCenter) / center


                            child.rotation = ratio * -3f
                            if (rightChild != null) {
                                rightChild.rotation = ratio * 3f
                            }


                            child.translationX = distanceFromCenter * 0.2f
                            child.translationY = ratio * 60f
                            child.translationZ = 1 - ratio
                        }
                    }
                })

            }

            is LastGamesViewHolder -> {
                data as HomeData.LastGames
                holder.binding.apply {
                    dataText.text = data.data
                    gameTypeText.text = data.lastGameType
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

    private fun manageCircleDay(dayItem: TrackingDayItemBinding, day: Day) {
        dayItem.fireDay.visibility = if (day.isDone) View.VISIBLE else View.INVISIBLE
        dayItem.elevation.visibility = if (day.isDone) View.VISIBLE else View.INVISIBLE
        dayItem.dayChar.text = day.prefName
        dayItem.dayCircle.setImageResource(
            if (day.isDone) R.drawable.circle_day_selected
            else R.drawable.circle_day
        )
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