package com.quriogamethechance.quriogame.ui

import com.quriogamethechance.quriogame.MainActivity
import com.quriogamethechance.quriogame.di.AppModule
import com.quriogamethechance.quriogame.di.LocalModule
import com.quriogamethechance.quriogame.di.NetworkModule
import com.quriogamethechance.quriogame.di.RepositoryModule
import com.quriogamethechance.quriogame.ui.achievement.AchievementFragment
import com.quriogamethechance.quriogame.ui.buyCharacter.BuyCharacterFragment
import com.quriogamethechance.quriogame.ui.buyLife.BuyLifeFragment
import com.quriogamethechance.quriogame.ui.character.CharacterFragment
import com.quriogamethechance.quriogame.ui.characterDetails.CharacterDetailsFragment
import com.quriogamethechance.quriogame.ui.gameResult.GameResultFragment
import com.quriogamethechance.quriogame.ui.game_fragment.GameFragment
import com.quriogamethechance.quriogame.ui.home.HomeFragment
import com.quriogamethechance.quriogame.ui.lastGames.LastGamesFragment
import com.quriogamethechance.quriogame.ui.main.MainFragment
import com.quriogamethechance.quriogame.ui.onboarding.FirstOnboardingFragment
import com.quriogamethechance.quriogame.ui.onboarding.FourthOnboardingFragment
import com.quriogamethechance.quriogame.ui.onboarding.SecondOnboardingFragment
import com.quriogamethechance.quriogame.ui.onboarding.ThirdOnboardingFragment
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
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MainFragment)

    fun inject(fragment: FirstOnboardingFragment)
    fun inject(fragment: SecondOnboardingFragment)
    fun inject(fragment: ThirdOnboardingFragment)
    fun inject(fragment: FourthOnboardingFragment)

    fun inject(fragment: BuyLifeFragment)
    fun inject(fragment: CharacterFragment)
    fun inject(fragment: CharacterDetailsFragment)
    fun inject(fragment: BuyCharacterFragment)

    fun inject(fragment: AchievementFragment)


}