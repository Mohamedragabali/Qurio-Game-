package com.quriogamethechance.quriogame.ui.buyLife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentBuyLifeBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class BuyLifeFragment : BaseDialogFragment<FragmentBuyLifeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBuyLifeBinding
        get() = FragmentBuyLifeBinding::inflate

    override fun setup() {
        initButtonText()
        initButton()
    }

    private fun initButton() {
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
    }
    private fun initButtonText() {
        val includedView = binding.buyButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.buy)
    }
}