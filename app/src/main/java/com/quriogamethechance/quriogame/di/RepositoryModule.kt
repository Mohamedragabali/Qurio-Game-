package com.quriogamethechance.quriogame.di

import com.quriogamethechance.quriogame.data.Repository
import com.quriogamethechance.quriogame.data.RepositoryImp
import com.quriogamethechance.quriogame.presenter.game.GamePresenter
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideRetrofit(repository: Repository): GamePresenter =
        GamePresenter(repository)

    @Provides
    fun provideRepository(): Repository =
        RepositoryImp()
}