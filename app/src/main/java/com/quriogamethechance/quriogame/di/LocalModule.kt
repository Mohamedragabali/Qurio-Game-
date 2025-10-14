package com.quriogamethechance.quriogame.di

import android.content.Context
import androidx.room.Room
import com.quriogamethechance.quriogame.data.local.lastGame.LastGameDao
import com.quriogamethechance.quriogame.data.local.QurioGameDatabase
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): QurioGameDatabase {
        return Room.databaseBuilder(context,
            QurioGameDatabase::class.java,
            "QURIO_GAME_DATABASE").build()
    }

    @Provides
    fun provideDao(db: QurioGameDatabase): LastGameDao = db.LastGameDao()
}