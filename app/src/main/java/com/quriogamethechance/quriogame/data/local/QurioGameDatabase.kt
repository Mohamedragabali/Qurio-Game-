package com.quriogamethechance.quriogame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameDao
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameEntity


@Database(entities = [LastGameEntity::class], version = 1)
abstract class QurioGameDatabase: RoomDatabase() {
    abstract fun LastGameDao(): LastGameDao
}