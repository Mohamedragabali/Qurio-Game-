package com.quriogamethechance.quriogame.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentFourthOnboardingBinding
import com.quriogamethechance.quriogame.ui.BaseFragment

class FourthOnboardingFragment : BaseFragment<FragmentFourthOnboardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFourthOnboardingBinding
        get() = FragmentFourthOnboardingBinding::inflate

    override fun setup() {

    }

}