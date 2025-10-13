package com.quriogamethechance.quriogame.di

import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.data.repository.RepositoryImp
import com.quriogamethechance.quriogame.presenter.game.GamePresenter
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideGamePresenter(repository: Repository): GamePresenter =
        GamePresenter(repository)

    @Provides
    fun provideRepository(apiService : GameApiService): Repository =
        RepositoryImp(apiService = apiService)
}