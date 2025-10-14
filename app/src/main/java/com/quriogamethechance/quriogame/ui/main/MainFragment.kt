package com.quriogamethechance.quriogame.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.databinding.FragmentMainBinding
import com.quriogamethechance.quriogame.presenter.main.MainPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import jakarta.inject.Inject


class MainFragment : BaseFragment<FragmentMainBinding>(), MainViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject
    lateinit var mainPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        mainPresenter.view = this
        mainPresenter.getIsAppOpenBefore()
    }

    override fun onGetAppOpenBefore(isOpenBefore: Boolean) {
        if(isOpenBefore){
            val action = MainFragmentDirections.actionMainFragmentToHomeFragment()
            binding.root.findNavController().navigate(action)
        }else{
            val action = MainFragmentDirections.actionMainFragmentToFirstOnboardingFragment()
            binding.root.findNavController().navigate(action)
        }

    }

    override fun onLoading() {
    }

    override fun onGetDataSuccess() {
    }

    override fun onGetDataError() {
    }

}