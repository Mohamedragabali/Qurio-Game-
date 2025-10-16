package com.quriogamethechance.quriogame.data.repository

import com.quriogamethechance.quriogame.data.local.achievement.AchievementEntity
import com.quriogamethechance.quriogame.data.local.charcter.CharacterEntity
import com.quriogamethechance.quriogame.data.remote.dto.GamesDto
import com.quriogamethechance.quriogame.presenter.setting.Setting
import com.quriogamethechance.quriogame.ui.lastGames.LastGame

interface Repository {
    suspend fun getGameQuestion(gameId: Int, difficulty: String): GamesDto

    suspend fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    )

    suspend fun getLastGames(): List<LastGame>

    suspend fun getIsAppOpenBefore():Boolean

    suspend fun setAppOpen()

    suspend fun setLives(lives:Int)
    suspend fun getLives():Int

    suspend fun setCoins(coins:Int)
    suspend fun getCoins():Int

    suspend fun cacheCharacter()

    suspend fun getAllCharacters(): List<CharacterEntity>

    fun getSelectedCharacter(): List<CharacterEntity>

    fun getCharacterByName(characterName: String): List<CharacterEntity>

    fun updateCharacter(characterEntity: CharacterEntity)

    fun cacheAchievement()

    fun updateAchievement(achievement: AchievementEntity)

    fun getAllAchievements(): List<AchievementEntity>

    fun getOpenAchievements(): List<AchievementEntity>

    fun getAchievementsByName(achievementNickName: String): AchievementEntity

    suspend fun getTrackingResult(weekDays :List<String>,currentDate: String):List<Boolean>

    suspend fun setSetting(soundDegree:Float,musicDegree:Float)

    suspend fun getSetting(): Setting
}