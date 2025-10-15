package com.quriogamethechance.quriogame.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentHomeBinding
import com.quriogamethechance.quriogame.presenter.home.HomePresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import com.quriogamethechance.quriogame.ui.home.gamesItem.GameItem
import com.quriogamethechance.quriogame.ui.lastGames.LastGame
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteraction , HomeViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    val adapter = HomeAdapter(emptyList(), this)

    private val itemsIndex = mapOf(
        HomeItemType.USER_INFORMATION to 0,
        HomeItemType.DASHBOARD to 1,
        HomeItemType.TRACKING_LOGIN to 2,
        HomeItemType.GAMES to 4,
        HomeItemType.LAST_GAMES to 5,
    )

    @Inject
    lateinit var homePresenter: HomePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        homePresenter.view = this
        homePresenter.getLastGames()
        homePresenter.getDashboardData()
        homePresenter.getCharacterInformation()
        binding.homeRecyclerView.adapter = adapter

        adapter.setData(
            listOf(
                HomeData.UserInformation(characterName = "", characterImage = R.drawable.rika),
                HomeData.Dashboard(livesCount = 0, pointsCount = 0, awardsCount = 0),
                HomeData.TrackingLogin(
                    listOf(
                        Day(
                            "S",
                            false
                        ),
                        Day(
                            "M",
                            false
                        ),
                        Day(
                            "T",
                            false
                        ),
                        Day(
                            "W",
                            false
                        ),
                        Day(
                            "Th",
                            false
                        ),
                        Day(
                            "F",
                            false
                        ),
                        Day(
                            "S",
                            false
                        )
                    )
                ),
                HomeData.Header(
                    headerTitle = "Games",
                    headerType = HomeHeaderType.GAMES
                ),
                HomeData.Games(
                    listOf(
                        GameItem(
                            type = "Geography",
                            image = R.drawable.geography,
                            id = 22
                        ),
                        GameItem(
                            type = "Science",
                            image = R.drawable.science,
                            id = 30
                        ),
                        GameItem(
                            type = "General Knowledge",
                            image = R.drawable.general_knowledge,
                            id = 9
                        ),
                        GameItem(
                            type = "Music",
                            image = R.drawable.music,
                            id = 12
                        ),
                        GameItem(
                            type = "Film & TV",
                            image = R.drawable.film_tv,
                            id = 11
                        ),
                        GameItem(
                            type = "Food & Drink",
                            image = R.drawable.food_drink,
                            id = 17
                        ),
                        GameItem(
                            type = "Society & Culture",
                            image = R.drawable.society_culture,
                            id = 24
                        ),
                        GameItem(
                            type = "History",
                            image = R.drawable.history,
                            id = 23
                        ),
                        GameItem(
                            type = "Arts & Literature",
                            image = R.drawable.arts_literature,
                            id = 25
                        ),
                    )
                ),
            )
        )
        homePresenter.getTrackingLogin(getWeekDaysDate())
        initUpdateDataFromActionDialog()

    }
    fun initUpdateDataFromActionDialog(){
        parentFragmentManager.setFragmentResultListener(Constant.UPDATE_DASHBOARD_DATA_KEY, this) { _, bundle ->
            homePresenter.getDashboardData()
        }

        parentFragmentManager.setFragmentResultListener(Constant.UPDATE_CHARACTER_INFORMATION_KEY, this) { _, bundle ->
            homePresenter.getCharacterInformation()
        }

    }

    override fun onClickCharacter() {
        val action = HomeFragmentDirections.actionHomeFragmentToCharacterFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickSitting() {
        val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickHeader(homeHeaderType: HomeHeaderType) {
        val action = HomeFragmentDirections
        when (homeHeaderType) {
            HomeHeaderType.GAMES -> binding.root.findNavController()
                .navigate(action.actionHomeFragmentToGamesFragment())

            HomeHeaderType.LAST_GAMES -> binding.root.findNavController()
                .navigate(action.actionHomeFragmentToLastGamesFragment())
        }
    }

    override fun onClickPlayNow(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDifficultyLevelFragment(id)
        binding.root.findNavController().navigate(action)
    }

    override fun onClickAddLive(pointsCount: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToBuyLifeFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onClickShowAward() {
        val action = HomeFragmentDirections.actionHomeFragmentToAchievementFragment()
        binding.root.findNavController().navigate(action)
    }

    override fun onGetLastGameSuccess(questionsList: List<LastGame>) {

       if(questionsList.isNotEmpty()){
           adapter.addItem(
               HomeData.Header(
                   headerTitle = "Last Games",
                   headerType = HomeHeaderType.LAST_GAMES
               )
           )
       }

        questionsList.forEach {
            adapter.addItem(HomeData.LastGames(
                data = it.data,
                lastGameType = it.type,
                coinsCount = it.coinsCount,
                starCount = it.starCount,
                gameTime = it.gameTime
            ))
        }
    }

    override fun onGetDashboardSuccess(livesCount: Int , coinsCount: Int , awardsCount: Int) {
        adapter.addItemInIndex(
            HomeData.Dashboard(
                livesCount = livesCount,
                pointsCount = coinsCount.toLong(),
                awardsCount = awardsCount
            ),
            itemsIndex[HomeItemType.DASHBOARD]!!
        )
    }

    override fun onGetCharacterInformationSuccess(
        characterName: String,
        characterImage: Int
    ) {
        adapter.addItemInIndex(
            HomeData.UserInformation(
                characterName = characterName,
                characterImage = characterImage
            ),
            itemsIndex[HomeItemType.USER_INFORMATION]!!
        )
    }

    override fun onGetTrackingLoginSuccess(trackingResultList: List<Boolean>) {
        val daysChar = listOf("S", "M", "T", "W", "Th", "F", "S")
        val newTrackingResultList = trackingResultList.mapIndexed { index, isDone ->
            Day(
                daysChar[index],
                isDone
            )
        }
        adapter.addItemInIndex(
            HomeData.TrackingLogin(
                newTrackingResultList
            ),
            itemsIndex[HomeItemType.TRACKING_LOGIN]!!
        )
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }

    private fun getWeekDaysDate():List<String>{
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.SUNDAY
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

        val weekDays = mutableListOf<String>()
        val simpleFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val firstDay = simpleFormat.format(calendar.time)
        val currentDay  = simpleFormat.format(Date())

        var dayBeforeCurrentDay = 0
        if(currentDay == firstDay){
            weekDays.add(currentDay)
        }else{
            weekDays.add(currentDay)
            val calendar2 = Calendar.getInstance()
            var todayString : String
            do {
                calendar2.add(Calendar.DAY_OF_YEAR, -1)
                calendar2.firstDayOfWeek = Calendar.SUNDAY
                todayString = simpleFormat.format(calendar2.time)
                weekDays.add(todayString)
                dayBeforeCurrentDay++
            }while (todayString != firstDay)
        }


        val calendar3 = Calendar.getInstance()
        var todayString2: String
        val remindDays =  7 - 1 - dayBeforeCurrentDay

        for (i in 0 until remindDays){
            calendar3.add(Calendar.DAY_OF_YEAR, 1)
            calendar3.firstDayOfWeek = Calendar.SUNDAY
            todayString2 = simpleFormat.format(calendar3.time)
            weekDays.add(todayString2)
        }

         weekDays.sort()
        return weekDays
    }

    object Constant{
        const val UPDATE_DASHBOARD_DATA_KEY = "UPDATE_DASHBOARD_DATA_KEY"
        const val UPDATE_CHARACTER_INFORMATION_KEY = "UPDATE_CHARACTER_INFORMATION_KEY"
    }
}