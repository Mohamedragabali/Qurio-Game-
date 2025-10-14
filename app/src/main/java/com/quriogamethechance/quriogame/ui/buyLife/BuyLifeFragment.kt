package com.quriogamethechance.quriogame.ui.buyLife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentBuyLifeBinding
import com.quriogamethechance.quriogame.presenter.buyAlive.BuyAlivePresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import com.quriogamethechance.quriogame.ui.home.HomeFragment
import jakarta.inject.Inject

class BuyLifeFragment : BaseDialogFragment<FragmentBuyLifeBinding>(), BuyLifeViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBuyLifeBinding
        get() = FragmentBuyLifeBinding::inflate

    @Inject
    lateinit var buyLifePresenter: BuyAlivePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        initButtonText()
        initButton()
        disableBuyButton()
        buyLifePresenter.view  = this
        buyLifePresenter.getCoins()

    }

    private fun initButton() {
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.buyButton.root.setOnClickListener {
            buyLifePresenter.buyAlive()

        }
    }
    private fun initButtonText() {
        val includedView = binding.buyButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.buy)
    }

    private fun enableMainButton() {
        binding.buyButton.apply {
            root.isClickable = true
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            endShadow.visibility = View.VISIBLE
            bottomShadow.visibility = View.VISIBLE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.second_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_with_radias)
        }
    }

    private fun disableBuyButton() {
        binding.buyButton.apply {
            root.isClickable = false
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.shade_tertiary))
            endShadow.visibility = View.GONE
            bottomShadow.visibility = View.GONE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
        }
    }

    override fun onGetCoinsSuccess(coinsCount: Int) {
        if(coinsCount >= 200 ){
            enableMainButton()
        }
    }

    override fun onBuyAliveSuccess() {
        val result = Bundle().apply {}
        parentFragmentManager.setFragmentResult(HomeFragment.Constant.UPDATE_DASHBOARD_DATA_KEY, result)
        dismiss()
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }


}