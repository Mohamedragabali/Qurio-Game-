package com.quriogamethechance.quriogame.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.CharcterBinding
import com.quriogamethechance.quriogame.databinding.FragmentCharacterBinding
import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.presenter.charcter.CharacterPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import jakarta.inject.Inject


class CharacterFragment : BaseDialogFragment<FragmentCharacterBinding>() , CharacterViewInterface{
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterBinding
        get() = FragmentCharacterBinding::inflate

    private var characterSelectedBinding : CharcterBinding? = null
    private var characterSelected : Character? = null


    @Inject
    lateinit var characterPresenter: CharacterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        characterPresenter.view = this
        characterPresenter.getCharacters()
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

        binding.confirmButton.root.setOnClickListener {
            Log.d("TAG", "setOnClickListener: ${characterSelected?.name}")
            val action = CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailsFragment(
                    characterSelected?.name.toString(),
                    characterSelected?.isOpen ?: false
                )
            findNavController().navigate(action)
        }
    }

    private fun initButtonText() {
        val includedView = binding.confirmButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.confirm)
    }

    private fun onClickCharacter(character: CharcterBinding){
       characterSelectedBinding?.selectIcon?.visibility = View.GONE
        character.selectIcon.visibility = View.VISIBLE
        characterSelectedBinding = character
    }

    override fun onGetCharactersSuccess(characters: List<Character>) {
        characters.forEach {characterItem ->
            val character = CharcterBinding.inflate(layoutInflater,binding.characters,false)
            if(characterItem.isOpen){
                character.characterImage.setImageResource(characterItem.openImage)
            }else{
                character.characterImage.setImageResource(characterItem.closeImage)
            }
            character.characterName.text = characterItem.name
            if(characterItem.isSelected){
                character.selectIcon.visibility = View.VISIBLE
                characterSelectedBinding = character
                characterSelected = characterItem
            }
            character.root.setOnClickListener {
                onClickCharacter(character)
                characterSelected = characterItem
            }
            binding.characters.addView(character.root)
        }
    }

    override fun onLoading() {
    }

    override fun onGetDataSuccess() {
    }

    override fun onGetDataError() {
    }


}
