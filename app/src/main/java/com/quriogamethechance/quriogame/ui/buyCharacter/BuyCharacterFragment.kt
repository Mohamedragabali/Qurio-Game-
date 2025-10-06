package com.quriogamethechance.quriogame.ui.buyCharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentBuyCharacterBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment
import com.quriogamethechance.quriogame.ui.characterDetails.CharacterDetailsFragmentArgs

class BuyCharacterFragment : BaseDialogFragment<FragmentBuyCharacterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBuyCharacterBinding
        get() = FragmentBuyCharacterBinding::inflate
    private val characterDetails: Map<String, Pair<Int,String>> =
        mapOf(
            "Rika" to (R.drawable.rika_image to "0"),
            "Kaiyo" to  (R.drawable.kaiyo_image to "300"),
            "Mimi" to (R.drawable.mimi_image to "700"),
            "Yoru" to (R.drawable.yoru_image to "1k"),
            "Kuro" to  (R.drawable.kuro_image to "3k"),
            "Miko" to (R.drawable.miko_image to "7k"),
            "Aori" to (R.drawable.aori_image to "12k"),
            "Nara" to (R.drawable.nara_image to "30k"),
            "Renji" to (R.drawable.renji_image  to "50k"),
        )
    override fun setup() {
        initButtonText()
        initButton()
        receiveData()
    }
    private fun receiveData() {
        val args = CharacterDetailsFragmentArgs.fromBundle(requireArguments())
        initialDetails(args.characterName )
    }

    private fun initialDetails(characterName: String) {
        val characterDetail = characterDetails[characterName]
        characterDetail?.apply {
            binding.characterImage.setImageResource(first)
            binding.price.text = second
        }
    }

    private fun initButton() {
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
    }

    private fun initButtonText() {
        val includedView = binding.buyButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.buy)
    }

}