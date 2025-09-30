package com.quriogamethechance.quriogame.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentMainBinding
import com.quriogamethechance.quriogame.ui.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() =  FragmentMainBinding::inflate

    override fun setup() {

    }

}