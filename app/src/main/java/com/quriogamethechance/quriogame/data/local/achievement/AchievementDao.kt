package com.quriogamethechance.quriogame.data.local.achievement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun insertAchievement(achievement: AchievementEntity)

    @Query("SELECT * FROM ACHIEVEMENT_TABLE")
    fun getAllAchievements(): List<AchievementEntity>

    @Query("SELECT * FROM ACHIEVEMENT_TABLE WHERE isOpen = :isOpen")
    fun getOpenAchievements(isOpen: Boolean = true): List<AchievementEntity>

    @Query("SELECT * FROM ACHIEVEMENT_TABLE WHERE nickname = :achievementNickName")
    fun getAchievementsByName(achievementNickName: String): List<AchievementEntity>

}