package com.quriogamethechance.quriogame.ui

import android.app.Application
class QurioApp : Application() {


    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}