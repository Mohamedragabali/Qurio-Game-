package com.quriogamethechance.quriogame.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.CharcterBinding
import com.quriogamethechance.quriogame.databinding.FragmentCharacterBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment


class CharacterFragment : BaseDialogFragment<FragmentCharacterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterBinding
        get() = FragmentCharacterBinding::inflate

    private val characterNames = listOf("Rika","Kaiyo","mimi","Yoru","Kuro","Miko","Aori","Nara","Renji")
    private var characterSelected : CharcterBinding? = null
    private val characters = listOf(
        R.drawable.rika to R.drawable.rika,
        R.drawable.kaiyo_open to R.drawable.kaiyo_close,
        R.drawable.mimi_open to R.drawable.mimi_close,
        R.drawable.yoru_open to R.drawable.yoru_close,
        R.drawable.kuro_open to R.drawable.kuro_close,
        R.drawable.miko_open to R.drawable.miko_close,
        R.drawable.aori_open to R.drawable.aori_close,
        R.drawable.nara_open to R.drawable.nara_close,
        R.drawable.renji_open to R.drawable.renji_close,
    )
    override fun setup() {
        initButton()
        initButtonText()
        initCharacter()
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
        val includedView = binding.confirmButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.confirm)
    }

    private fun initCharacter() {
        characters.forEachIndexed {index,(openImage, closeImage)->
            val character = CharcterBinding.inflate(layoutInflater,binding.characters,false)
            character.characterImage.setImageResource(closeImage)
            character.characterName.text = characterNames[index]
            if (index == 0 ){
                character.selectIcon.visibility = View.VISIBLE
                characterSelected = character
            }
            character.root.setOnClickListener {
                onClickCharacter(character)
            }
            binding.characters.addView(character.root)


        }

    }

    private fun onClickCharacter(character: CharcterBinding){
       characterSelected?.selectIcon?.visibility = View.GONE
        character.selectIcon.visibility = View.VISIBLE
        characterSelected = character
    }


}
