package com.quriogamethechance.quriogame.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.data.local.LastGameDao
import com.quriogamethechance.quriogame.data.local.LastGameEntity
import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import com.quriogamethechance.quriogame.ui.games.Game
import com.quriogamethechance.quriogame.ui.lastGames.LastGame
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class RepositoryImp @Inject constructor(
    private val apiService: GameApiService,
    private val lastGameDao: LastGameDao,
    private val preferencesDataStore: DataStore<Preferences>
) : Repository {
    private val gameQuestionAmount = 12
    private val gameType = "multiple"

    private val keyIsAppOpenBefore = booleanPreferencesKey("IS_APP_OPEN_BEFORE")


    override suspend fun getGameQuestion(
        gameId: Int, difficulty: String
    ): GamesDto = apiService.getGames(
        amount = gameQuestionAmount,
        gameId = gameId,
        gameDifficulty = difficulty,
        gameType = gameType
    )

    override suspend fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    ) {
        lastGameDao.insertLastGame(
            LastGameEntity(
                typeId = typeId,
                coinsCount = coinsCount,
                starCount = starCount,
                time = time,
                date = date
            )
        )

    }

    override suspend fun getLastGames(): List<LastGame> {
        return lastGameDao.getAllLastGames().reversed().map {
            LastGame(
                type = findTypeIdText(it.typeId),
                coinsCount = it.coinsCount.toLong(),
                starCount = it.starCount.toLong(),
                gameTime = convertTimeToString(it.time),
                data = it.date
            )
        }
    }

    override suspend fun getIsAppOpenBefore(): Boolean =
        preferencesDataStore.data.first()[keyIsAppOpenBefore] ?: false

    private fun findTypeIdText(typeId: Int): String {
        val gamesData = listOf(
            Game(
                type = "Geography",
                image = R.drawable.geography,
                id = 22
            ),
            Game(
                type = "Science",
                image = R.drawable.science,
                id = 30
            ),
            Game(
                type = "General Knowledge",
                image = R.drawable.general_knowledge,
                id = 9
            ),
            Game(
                type = "Music",
                image = R.drawable.music,
                id = 12
            ),
            Game(
                type = "Film & TV",
                image = R.drawable.film_tv,
                id = 11
            ),
            Game(
                type = "Food & Drink",
                image = R.drawable.food_drink,
                id = 17
            ),
            Game(
                type = "Society & Culture",
                image = R.drawable.society_culture,
                id = 24
            ),
            Game(
                type = "History",
                image = R.drawable.history,
                id = 23
            ),
            Game(
                type = "Arts & Literature",
                image = R.drawable.arts_literature,
                id = 25
            ),
        )
        return gamesData.find { it.id == typeId }?.type ?: "not found"
    }

    private fun convertTimeToString(time: Int): String {
        val minute = time / 60
        val second = time % 60
        val secondText = if (second < 9) "0$second" else "$second"
        val timeString = if (minute <= 0) "${second}sec" else "${minute}m ${secondText}sec"
        return timeString
    }

}