package com.quriogamethechance.quriogame.di

import com.quriogamethechance.quriogame.data.local.LastGameDao
import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.data.repository.RepositoryImp
import com.quriogamethechance.quriogame.presenter.game.GamePresenter
import com.quriogamethechance.quriogame.presenter.gameResult.GameResultPresenter
import com.quriogamethechance.quriogame.presenter.lastGamePresenter.LastGamePresenter
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideRepository(apiService : GameApiService,lastGameDao: LastGameDao): Repository =
        RepositoryImp(apiService = apiService,lastGameDao = lastGameDao)

    @Provides
    fun provideGamePresenter(repository: Repository): GamePresenter =
        GamePresenter(repository)

    @Provides
    fun provideLastGamePresenter(repository: Repository): LastGamePresenter =
        LastGamePresenter(repository)

    @Provides
    fun provideGameResultPresenter(repository: Repository): GameResultPresenter =
        GameResultPresenter(repository)
}