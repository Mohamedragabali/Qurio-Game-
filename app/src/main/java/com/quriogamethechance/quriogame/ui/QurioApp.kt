package com.quriogamethechance.quriogame.ui

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.quriogamethechance.quriogame.di.AppModule

class QurioApp : Application() {


    lateinit var appComponent: AppComponent
        private set

    private val preferencesName = "APP_PREFERENCES"
    val Context.preferencesDataStore by preferencesDataStore("APP_PREFERENCES")

    override fun onCreate() {
        super.onCreate()
        appComponent =DaggerAppComponent.builder()
            .appModule(AppModule(this,preferencesDataStore))
            .build()
    }
}