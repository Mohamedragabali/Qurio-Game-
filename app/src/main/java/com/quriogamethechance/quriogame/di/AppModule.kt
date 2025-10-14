package com.quriogamethechance.quriogame.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
class AppModule(private val application: Application , private val preferencesDataStore: DataStore<Preferences>) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun providePreferencesDataStore(): DataStore<Preferences> {
        return preferencesDataStore
    }
}