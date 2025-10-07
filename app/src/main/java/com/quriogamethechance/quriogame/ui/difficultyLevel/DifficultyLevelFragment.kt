package com.quriogamethechance.quriogame.ui.difficultyLevel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentDifficultyLevelBinding
import com.quriogamethechance.quriogame.databinding.LevelButtonBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class DifficultyLevelFragment : BaseDialogFragment<FragmentDifficultyLevelBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDifficultyLevelBinding
        get() = FragmentDifficultyLevelBinding::inflate

    override fun setup() {
        initDisableConfirmButton()
        initButtonText()
        initButton()
    }


    private fun initButtonText() {
        binding.confirmButton.buttonText.text = getString(R.string.confirm)
        (binding.easyButton.cardContainer.children.first() as TextView ).text =
            getString(R.string.easy)
        (binding.mediumButton.cardContainer.children.first() as TextView ).text =
            getString(R.string.medium)
        (binding.hardButton.cardContainer.children.first() as TextView ).text =
            getString(R.string.hard)

    }

    private fun initButton() {
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.easyButton.root.setOnClickListener {
            choiceDifficultLevel(binding.easyButton)
        }
        binding.mediumButton.root.setOnClickListener {
            choiceDifficultLevel(binding.mediumButton)

        }
        binding.hardButton.root.setOnClickListener {
            choiceDifficultLevel(binding.hardButton)

        }
    }

    private fun choiceDifficultLevel(levelButton : LevelButtonBinding){
        enableConfirmButton()
        val primaryColor = ContextCompat.getColorStateList(requireContext(), R.color.primary)
        val secondaryColor = ContextCompat.getColorStateList(requireContext(), R.color.surface_high)
        val buttons = listOf(binding.easyButton,binding.mediumButton,binding.hardButton)
        buttons.forEach {button->
            if(button == levelButton){
                button.shadowImage.visibility = View.VISIBLE
                button.cardContainer.backgroundTintList = primaryColor
                button.levelText.setTextColor(ContextCompat.getColor(requireContext(),R.color.on_primary))
            }else{
                button.shadowImage.visibility = View.GONE
                button.cardContainer.backgroundTintList = secondaryColor
                button.levelText.setTextColor(ContextCompat.getColor(requireContext(),R.color.shade_secondary))
            }
        }
    }

    private fun initDisableConfirmButton() {
        binding.confirmButton.apply {
            root.isClickable = false
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.shade_tertiary))
            endShadow.visibility = View.GONE
            bottomShadow.visibility = View.GONE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
        }
    }
    private fun enableConfirmButton(){
        binding.confirmButton.apply {
            root.isClickable = true
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            endShadow.visibility = View.VISIBLE
            bottomShadow.visibility = View.VISIBLE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.second_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_with_radias)
        }
    }

}