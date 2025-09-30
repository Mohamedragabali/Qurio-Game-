package com.quriogamethechance.quriogame.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentSecondOnboardingBinding
import com.quriogamethechance.quriogame.ui.BaseFragment

class SecondOnboardingFragment : BaseFragment<FragmentSecondOnboardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecondOnboardingBinding
        get() = FragmentSecondOnboardingBinding::inflate

    override fun setup() {

    }

}