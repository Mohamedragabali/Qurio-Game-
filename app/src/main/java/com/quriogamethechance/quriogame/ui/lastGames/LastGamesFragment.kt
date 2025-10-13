package com.quriogamethechance.quriogame.ui.lastGames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentLastGamesBinding
import com.quriogamethechance.quriogame.presenter.lastGamePresenter.LastGamePresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import jakarta.inject.Inject


class LastGamesFragment : BaseFragment<FragmentLastGamesBinding>(), LastGameViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLastGamesBinding
        get() =  FragmentLastGamesBinding::inflate

    @Inject
    lateinit var lastGamePresenter: LastGamePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    val  adapter : LastGamesAdapter = LastGamesAdapter(emptyList())
    override fun setup() {
        lastGamePresenter.view = this
        lastGamePresenter.getLastGames()
        setHeader()
        setAdapter()
    }

    private fun setAdapter() {
        binding.lastGames.adapter = adapter
    }

    private fun setHeader() {
        binding.header.headerText.text = getString(R.string.last_games)
        binding.header.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onGetLastGameSuccess(questionsList: List<LastGame>) {
        adapter.setData(
            questionsList
        )
        onGetDataSuccess()
    }

    override fun onLoading() {
        binding.header.root.visibility = View.INVISIBLE
        binding.lastGames.visibility = View.INVISIBLE
        binding.loadingAnimationText.visibility = View.VISIBLE
        binding.loadingAnimationIcon.visibility = View.VISIBLE
    }

    override fun onGetDataSuccess() {
        binding.header.root.visibility = View.VISIBLE
        binding.lastGames.visibility = View.VISIBLE
        binding.loadingAnimationText.visibility = View.INVISIBLE
        binding.loadingAnimationIcon.visibility = View.INVISIBLE
    }

    override fun onGetDataError() {
    }
}