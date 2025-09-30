package com.quriogamethechance.quriogame.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentFirstOnboardingBinding
import com.quriogamethechance.quriogame.ui.BaseFragment

class FirstOnboardingFragment : BaseFragment<FragmentFirstOnboardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFirstOnboardingBinding
        get() = FragmentFirstOnboardingBinding::inflate

    override fun setup() {

    }

}