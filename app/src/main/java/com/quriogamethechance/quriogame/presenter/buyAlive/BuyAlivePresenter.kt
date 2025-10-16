package com.quriogamethechance.quriogame.presenter.buyAlive

import com.quriogamethechance.quriogame.data.repository.Repository
import com.quriogamethechance.quriogame.presenter.base.BasePresenter
import com.quriogamethechance.quriogame.ui.buyLife.BuyLifeViewInterface
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BuyAlivePresenter @Inject constructor(
    private val repository: Repository,
    private val uiContext: CoroutineContext = Dispatchers.Main) : BasePresenter() , CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext get() = uiContext + job

    fun buyAlive(){
        launch {
            try {
                val livePrice =
                    withContext(Dispatchers.IO) {
                        repository.getLivePrice()
                    }
                val alive = withContext(Dispatchers.IO) {
                    repository.getLives()
                }
                withContext(Dispatchers.IO) {
                    repository.setLives(alive + 1 )
                }
                val coins =
                    withContext(Dispatchers.IO) {
                        repository.getCoins()
                    }

                withContext(Dispatchers.IO) {
                    repository.setCoins(coins-livePrice)
                }
                (view as BuyLifeViewInterface).onBuyAliveSuccess()

            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }

    fun getLivePrice(){
        launch {
            try {
                val livePrice =
                    withContext(Dispatchers.IO) {
                        repository.getLivePrice()
                    }
                val coins =
                    withContext(Dispatchers.IO) {
                        repository.getCoins()
                    }
                (view as BuyLifeViewInterface).onGetLivePriceSuccess(
                    price = livePrice , coinsCount = coins
                )
            } catch (_: Exception) {
                view.onGetDataError()
            }
        }
    }
}