package com.quriogamethechance.quriogame.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentThirdOnboardingBinding
import com.quriogamethechance.quriogame.ui.BaseFragment

class ThirdOnboardingFragment : BaseFragment<FragmentThirdOnboardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentThirdOnboardingBinding
        get() = FragmentThirdOnboardingBinding::inflate

    override fun setup() {

    }

}