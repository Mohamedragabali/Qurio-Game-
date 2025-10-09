package com.quriogamethechance.quriogame.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentSettingsBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class SettingsFragment : BaseDialogFragment<FragmentSettingsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    override fun setup() {
        initButton()
        initButtonText()
    }

    private fun initButton() {
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }
    private fun initButtonText() {
        binding.saveChanges.buttonText.text = getString(R.string.save_changes)
    }

}