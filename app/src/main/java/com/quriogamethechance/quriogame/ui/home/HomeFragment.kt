package com.quriogamethechance.quriogame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentHomeBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() =  FragmentHomeBinding::inflate

    override fun setup() {

    }

}