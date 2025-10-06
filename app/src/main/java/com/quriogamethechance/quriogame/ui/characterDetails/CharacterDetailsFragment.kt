package com.quriogamethechance.quriogame.ui.characterDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentCharacterDetialsBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class CharacterDetailsFragment : BaseDialogFragment<FragmentCharacterDetialsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetialsBinding
        get() = FragmentCharacterDetialsBinding::inflate

    private val characterDetails: Map<String, CharacterDetails> =
        mapOf(
            "Rika" to CharacterDetails(
                name = "Rika",
                age = "Age: 12 Sunblooms",
                description = "Nature’s little explorer! Rika talks to mushrooms and swears squirrels give her battle advice. Always ready for an adventure.",
                imageResourceId = R.drawable.rika_image
            ),
            "Kaiyo" to CharacterDetails(
                name = "Kaiyo",
                age = "Age: 14 Storms",
                description = "A calm storm in human form. Kaiyo trains with ancient scrolls by day and drinks spicy tea by night. Sword sharp, heart sharper.",
                imageResourceId = R.drawable.kaiyo_image
            ),
            "Mimi" to CharacterDetails(
                name = "Mimi",
                age = "Age: 10 Volcano Puffs",
                description = "Tiny but terrifying! Mimi is always grumpy, but don’t let that scare you—unless you like pranks involving firecrackers.",
                imageResourceId = R.drawable.mimi_image
            ),
            "Yoru" to CharacterDetails(
                name = "Yoru",
                age = "Age: 13 Shadows",
                description = "Quiet, mysterious, and probably watching you right now. Yoru shows up when you least expect it.",
                imageResourceId = R.drawable.yoru_image
            ),
            "Kuro" to CharacterDetails(
                name = "Kuro",
                age = "Age: 15 Thunder Beats",
                description = "Cool jacket, cooler moves. Kuro never backs down from a challenge .",
                imageResourceId = R.drawable.kuro_image
            ),
            "Miko" to CharacterDetails(
                name = "Miko",
                age = "Age: 11 Leaf Turns",
                description = "Energetic, cheerful, and faster than a leaf in the wind. Miko can turn any trivia into a giggle-fest.",
                imageResourceId = R.drawable.miko_image
            ),
            "Aori" to CharacterDetails(
                name = "Aori",
                age = "Age: 13 Blade Echoes",
                description = "The sword chooses the wielder—and it chose Aori. Calm, focused.",
                imageResourceId = R.drawable.aori_image
            ),
            "Nara" to CharacterDetails(
                name = "Nara",
                age = "Age: 12 Crystal Songs",
                description = "Part magic, part sass. Nara sparkles even when she’s mad.",
                imageResourceId = R.drawable.nara_image
            ),
            "Renji" to CharacterDetails(
                name = "Renji",
                age = "Age: 11 Hero Coins",
                description = "Small but mighty! Renji dreams of glory, carries a shield too big for him.",
                imageResourceId = R.drawable.renji_image
            ),
        )
    override fun setup() {
        initButtonText()
        receiveData()
        initButton()
    }

    private fun initButton() {
        binding.okButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun initButtonText() {
        val includedView = binding.buy
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.buy)
    }

    private fun receiveData() {
        val args = CharacterDetailsFragmentArgs.fromBundle(requireArguments())
        initialDetails(args.characterName ,args.isOpenCharacter )
    }

    private fun initialDetails(characterName:String , characterIsOpened: Boolean) {
        val characterDetail = characterDetails[characterName]
        characterDetail?.apply {
            binding.characterName.text = name
            binding.characterAge.text = age
            binding.characterDescription.text = description
            binding.characterImage.setImageResource(imageResourceId)

            binding.lockImage.visibility = if (characterIsOpened) View.GONE else View.VISIBLE
            binding.buy.root.visibility = if (characterIsOpened) View.GONE else View.VISIBLE

        }


    }
}