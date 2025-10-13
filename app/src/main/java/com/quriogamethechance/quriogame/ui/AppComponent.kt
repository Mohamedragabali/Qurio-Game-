package com.quriogamethechance.quriogame.ui

import com.quriogamethechance.quriogame.MainActivity
import com.quriogamethechance.quriogame.di.AppModule
import com.quriogamethechance.quriogame.di.LocalModule
import com.quriogamethechance.quriogame.di.NetworkModule
import com.quriogamethechance.quriogame.di.RepositoryModule
import com.quriogamethechance.quriogame.ui.gameResult.GameResultFragment
import com.quriogamethechance.quriogame.ui.game_fragment.GameFragment
import com.quriogamethechance.quriogame.ui.lastGames.LastGamesFragment
import dagger.Component
import jakarta.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, LocalModule::class, RepositoryModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: QurioApp)
    fun inject(activity: MainActivity)
    fun inject(fragment: GameFragment)
    fun inject(fragment: LastGamesFragment)
    fun inject(fragment: GameResultFragment)

}