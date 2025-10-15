package com.quriogamethechance.quriogame.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.data.local.QurioGameDatabase
import com.quriogamethechance.quriogame.data.local.achievement.AchievementEntity
import com.quriogamethechance.quriogame.data.local.charcter.CharacterEntity
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameEntity
import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.games.Game
import com.quriogamethechance.quriogame.ui.lastGames.LastGame
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class RepositoryImp @Inject constructor(
    private val apiService: GameApiService,
    private val qurioiGameDatabase: QurioGameDatabase,
    private val preferencesDataStore: DataStore<Preferences>
) : Repository {
    private val gameQuestionAmount = 12
    private val gameType = "multiple"

    private val keyIsAppOpenBefore = booleanPreferencesKey("IS_APP_OPEN_BEFORE")
    private val keyLives = intPreferencesKey("LIVES")
    private val keyCoins = intPreferencesKey("COINS")


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
        qurioiGameDatabase.LastGameDao().insertLastGame(
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
        return qurioiGameDatabase.LastGameDao().getAllLastGames().reversed().map {
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

    override suspend fun setAppOpen() {
        preferencesDataStore.edit { prefs ->
            prefs[keyIsAppOpenBefore] = true
        }
    }

    override suspend fun setLives(lives: Int) {
        preferencesDataStore.edit { prefs ->
            prefs[keyLives] = lives
        }
    }

    override suspend fun getLives(): Int  =
        preferencesDataStore.data.first()[keyLives] ?: 0

    override suspend fun setCoins(coins: Int) {
        preferencesDataStore.edit { prefs ->
            prefs[keyCoins] = coins
        }
    }

    override suspend fun getCoins(): Int =
        preferencesDataStore.data.first()[keyCoins] ?: 0

    override suspend fun cacheCharacter() {
        val isEmpty = qurioiGameDatabase.CharacterDao().getAllCharacter().isEmpty()
        if(isEmpty) {
            val characters = listOf(
                Character(
                    name = "Rika",
                    description = "Nature’s little explorer! Rika talks to mushrooms and swears squirrels give her battle advice. Always ready for an adventure.",
                    age = "Age: 12 Sunblooms",
                    price = 0,
                    isOpen = true,
                    openImage = R.drawable.rika,
                    closeImage = R.drawable.rika,
                    characterImage = R.drawable.rika_image,
                    isSelected = true
                ),
                Character(
                    name = "Kaiyo",
                    description = "A calm storm in human form. Kaiyo trains with ancient scrolls by day and drinks spicy tea by night. Sword sharp, heart sharper.",
                    age = "Age: 14 Storms",
                    price = 300,
                    isOpen = false,
                    openImage = R.drawable.kaiyo_open,
                    closeImage = R.drawable.kaiyo_close,
                    characterImage = R.drawable.kaiyo_image,
                    isSelected = false
                ),
                Character(
                    name = "Mimi",
                    description = "Tiny but terrifying! Mimi is always grumpy, but don’t let that scare you—unless you like pranks involving firecrackers.",
                    age = "Age: 10 Volcano Puffs",
                    price = 700,
                    isOpen = false,
                    openImage = R.drawable.mimi_open,
                    closeImage = R.drawable.mimi_close,
                    characterImage = R.drawable.mimi_image,
                    isSelected = false
                ),
                Character(
                    name = "Yoru",
                    description = "Quiet, mysterious, and probably watching you right now. Yoru shows up when you least expect it.",
                    age = "Age: 13 Shadows",
                    price = 1000,
                    isOpen = false,
                    openImage = R.drawable.yoru_open,
                    closeImage = R.drawable.yoru_close,
                    characterImage = R.drawable.yoru_image,
                    isSelected = false
                ),
                Character(
                    name = "Kuro",
                    description = "Cool jacket, cooler moves. Kuro never backs down from a challenge .",
                    age = "Age: 15 Thunder Beats",
                    price = 3000,
                    isOpen = false,
                    openImage = R.drawable.kuro_open,
                    closeImage = R.drawable.kuro_close,
                    characterImage = R.drawable.kuro_image,
                    isSelected = false
                ),
                Character(
                    name = "Miko",
                    description = "Energetic, cheerful, and faster than a leaf in the wind. Miko can turn any trivia into a giggle-fest.",
                    age = "Age: 11 Leaf Turns",
                    price = 7000,
                    isOpen = false,
                    openImage = R.drawable.miko_open,
                    closeImage = R.drawable.miko_close,
                    characterImage = R.drawable.miko_image,
                    isSelected = false
                ),
                Character(
                    name = "Aori",
                    description = "The sword chooses the wielder—and it chose Aori. Calm, focused.",
                    age = "Age: 13 Blade Echoes",
                    price = 12000,
                    isOpen = false,
                    openImage = R.drawable.aori_open,
                    closeImage = R.drawable.aori_close,
                    characterImage = R.drawable.aori_image,
                    isSelected = false
                ),
                Character(
                    name = "Nara",
                    description = "Part magic, part sass. Nara sparkles even when she’s mad.",
                    age = "Age: 12 Crystal Songs",
                    price = 30000,
                    isOpen = false,
                    openImage = R.drawable.nara_open,
                    closeImage = R.drawable.nara_close,
                    characterImage = R.drawable.nara_image,
                    isSelected = false
                ),
                Character(
                    name = "Renji",
                    description = "Small but mighty! Renji dreams of glory, carries a shield too big for him.",
                    age = "Age: 11 Hero Coins",
                    price = 50000,
                    isOpen = false,
                    openImage = R.drawable.renji_open,
                    closeImage = R.drawable.renji_close,
                    characterImage = R.drawable.renji_image,
                    isSelected = false
                ),
            )
            characters.forEach {character ->
                qurioiGameDatabase.CharacterDao().insertCharacter(
                    CharacterEntity(
                        name = character.name,
                        description = character.description,
                        age = character.age,
                        price = character.price,
                        isOpen = character.isOpen,
                        openImage = character.openImage,
                        closeImage = character.closeImage,
                        characterImage = character.characterImage,
                        isSelected = character.isSelected
                    )
                )
            }
        }
    }

    override suspend fun getAllCharacters(): List<CharacterEntity> {
        return qurioiGameDatabase.CharacterDao().getAllCharacter()
    }

    override fun getSelectedCharacter(): List<CharacterEntity> {
        return qurioiGameDatabase.CharacterDao().getSelectedCharacter()
    }

    override fun getCharacterByName(characterName: String): List<CharacterEntity> {
        return qurioiGameDatabase.CharacterDao().getCharacterByName(characterName)
    }

    override fun updateCharacter(characterEntity: CharacterEntity) {
        qurioiGameDatabase.CharacterDao().insertCharacter(characterEntity)
    }

    override fun cacheAchievement() {
        val isEmpty = qurioiGameDatabase.CharacterDao().getAllCharacter().isEmpty()
        if(isEmpty) {
            val achievement = listOf(
                Achievement(
                    name = "Quiz Rookie",
                    description = "Welcome to the quiz world! You’ve just started your journey.",
                    id = 0,
                    nickname = "Quiz Rookie",
                    isOpen = false,
                    openImage =  R.drawable.quiz_rookie,
                    closeImage = R.drawable.quiz_rookie_closed,
                    howToGetIt = "Complete your first quiz.",
                ),
                Achievement(
                    name = "Streak Starter",
                    description = "You’re getting into rhythm!",
                    id = 0,
                    nickname = "Streak Starter",
                    isOpen = false,
                    openImage =  R.drawable.streak_starter,
                    closeImage = R.drawable.streak_starter_closed,
                    howToGetIt = "Answer 3 questions correctly in a row.",
                ),
                Achievement(
                    name = "Lucky Gueses",
                    description = "You’ve proven your luck!",
                    id = 0,
                    nickname = "Lucky Gueses",
                    isOpen = false,
                    openImage =  R.drawable.lucky_guess,
                    closeImage = R.drawable.lucky_guess_closed,
                    howToGetIt = "Answer a question correctly after selecting randomly within 2 seconds.",
                ),
                Achievement(
                    name = "Explorer",
                    description = "You love experimenting and discovering! You've tried several categories and aren't limited to just one.",
                    id = 0,
                    nickname = "Explorer",
                    isOpen = false,
                    openImage =  R.drawable.explorer,
                    closeImage = R.drawable.explorer_closed,
                    howToGetIt = "CPlay in at least 4 different categories.",
                ),
                Achievement(
                    name = "rivia Champ",
                    description = "You’re the best of the best!",
                    id = 0,
                    nickname = "Trivia Champ",
                    isOpen = false,
                    openImage =  R.drawable.trivia_champ,
                    closeImage = R.drawable.trivia_champ_closed,
                    howToGetIt = "Reach the top score in any category.",
                ),
                Achievement(
                    name = "Collector",
                    description = "You’ve collected quite a few wins!",
                    id = 0,
                    nickname = "Collector",
                    isOpen = false,
                    openImage =  R.drawable.collector,
                    closeImage = R.drawable.collector_closed,
                    howToGetIt = "Unlock 5 different achievements.",
                ),
                Achievement(
                    name = "Legend",
                    description = "Your name echoes across the quiz halls!",
                    id = 0,
                    nickname = "Legend",
                    isOpen = false,
                    openImage =  R.drawable.legend,
                    closeImage = R.drawable.legend_closed,
                    howToGetIt = "Reach level 50 or higher."
                ),
                Achievement(
                    name = "Untouchable",
                    description = "You rarely make mistakes! You're at a very high level and dominate the game.",
                    id = 0,
                    nickname = "Untouchable",
                    isOpen = false,
                    openImage =  R.drawable.untouchable,
                    closeImage = R.drawable.untouchable_closed,
                    howToGetIt = "Correctly answer 10 consecutive questions in a single game without making a single mistake."
                ),
                Achievement(
                    name = "Quick Thinker",
                    description = "You’re fast and accurate!",
                    id = 0,
                    nickname = "Quick Thinker",
                    isOpen = false,
                    openImage =  R.drawable.quick_thinker,
                    closeImage = R.drawable.quick_thinker_closed,
                    howToGetIt = "Answer 5 questions correctly in less than 10 seconds each."
                ),
                Achievement(
                    name = "Collector",
                    description = "You’re a master collector!",
                    id = 0,
                    nickname = "Collector2",
                    isOpen = false,
                    openImage =  R.drawable.collector2,
                    closeImage = R.drawable.collector2_closed,
                    howToGetIt = "Unlock 10 different achievements."
                ),
                Achievement(
                    name = "Lucky Guess",
                    description = "You’ve proven your luck!",
                    id = 0,
                    nickname = "Lucky Guess2",
                    isOpen = false,
                    openImage =  R.drawable.lucky_guess2,
                    closeImage = R.drawable.lucky_guess2_closed,
                    howToGetIt = "Make two correct random guesses in one session."
                )
            )
            achievement.forEach {character ->
                qurioiGameDatabase.AchievementDao().insertAchievement(
                    AchievementEntity(
                        name = character.name,
                        description = character.description,
                        id = character.id,
                        nickname = character.nickname,
                        isOpen = character.isOpen,
                        openImage = character.openImage,
                        closeImage = character.closeImage,
                        howToGetIt = character.howToGetIt,
                    )
                )
            }
        }
    }

    override fun updateAchievement(achievement: AchievementEntity) {
        qurioiGameDatabase.AchievementDao().insertAchievement(achievement)
    }

    override fun getAllAchievements(): List<AchievementEntity> {
        return  qurioiGameDatabase.AchievementDao().getAllAchievements()
    }

    override fun getOpenAchievements(): List<AchievementEntity> {
        return qurioiGameDatabase.AchievementDao().getOpenAchievements()
    }

    override fun getAchievementsByName(achievementNickName: String): AchievementEntity {
       return qurioiGameDatabase.AchievementDao().getAchievementsByName(achievementNickName).first()
    }

    override suspend fun getTrackingResult(weekDays :List<String>): List<Boolean> {
        val lastGames = getLastGames()
        val trackingLogin  = mutableListOf<Boolean>(false,false,false,false,false,false,false)
        lastGames.forEach {
            if(weekDays.contains(it.data)) {
                trackingLogin[weekDays.indexOf(it.data)] = true
            }
        }
        return trackingLogin
    }

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