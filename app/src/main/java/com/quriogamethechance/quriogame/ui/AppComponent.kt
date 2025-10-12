package com.quriogamethechance.quriogame.ui

import com.quriogamethechance.quriogame.MainActivity
import com.quriogamethechance.quriogame.di.LocalModule
import com.quriogamethechance.quriogame.di.NetworkModule
import com.quriogamethechance.quriogame.di.RepositoryModule
import com.quriogamethechance.quriogame.ui.game_fragment.GameFragment
import dagger.Component
import jakarta.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(app: QurioApp)
    fun inject(activity: MainActivity)
    fun inject(fragment: GameFragment)


}