package com.quriogamethechance.quriogame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LastGameEntity::class], version = 1)
abstract class QurioGameDatabase: RoomDatabase() {
    abstract fun LastGameDao(): LastGameDao
}