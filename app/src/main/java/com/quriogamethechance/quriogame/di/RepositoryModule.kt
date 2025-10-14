package com.quriogamethechance.quriogame.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.quriogamethechance.quriogame.data.local.LastGameDao
import com.quriogamethechance.quriogame.data.remote.GameApiService
import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.data.repository.RepositoryImp
import com.quriogamethechance.quriogame.presenter.game.GamePresenter
import com.quriogamethechance.quriogame.presenter.gameResult.GameResultPresenter
import com.quriogamethechance.quriogame.presenter.home.HomePresenter
import com.quriogamethechance.quriogame.presenter.lastGamePresenter.LastGamePresenter
import com.quriogamethechance.quriogame.presenter.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideRepository(
        apiService : GameApiService,
        lastGameDao: LastGameDao,
        preferencesDataStore : DataStore<Preferences>
    ): Repository =
        RepositoryImp(apiService = apiService,
            lastGameDao = lastGameDao,
            preferencesDataStore = preferencesDataStore)

    @Provides
    fun provideGamePresenter(repository: Repository): GamePresenter =
        GamePresenter(repository)

    @Provides
    fun provideLastGamePresenter(repository: Repository): LastGamePresenter =
        LastGamePresenter(repository)

    @Provides
    fun provideGameResultPresenter(repository: Repository): GameResultPresenter =
        GameResultPresenter(repository)

    @Provides
    fun provideHomePresenter(repository: Repository): HomePresenter =
        HomePresenter(repository)

    @Provides
    fun provideMainPresenter(repository: Repository): MainPresenter =
        MainPresenter(repository)
}