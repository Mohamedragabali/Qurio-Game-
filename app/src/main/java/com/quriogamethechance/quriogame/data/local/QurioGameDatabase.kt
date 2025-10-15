package com.quriogamethechance.quriogame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quriogamethechance.quriogame.data.local.achievement.AchievementDao
import com.quriogamethechance.quriogame.data.local.achievement.AchievementEntity
import com.quriogamethechance.quriogame.data.local.charcter.CharacterDao
import com.quriogamethechance.quriogame.data.local.charcter.CharacterEntity
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameDao
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameEntity


@Database(entities = [LastGameEntity::class, CharacterEntity::class, AchievementEntity::class], version = 1)
abstract class QurioGameDatabase: RoomDatabase() {
    abstract fun LastGameDao(): LastGameDao

    abstract fun CharacterDao(): CharacterDao

    abstract fun AchievementDao(): AchievementDao
}