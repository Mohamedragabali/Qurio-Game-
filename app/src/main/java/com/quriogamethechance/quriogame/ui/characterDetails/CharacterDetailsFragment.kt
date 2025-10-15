package com.quriogamethechance.quriogame.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentCharacterDetialsBinding
import com.quriogamethechance.quriogame.presenter.characterDetails.CharacterDetailsPresenter
import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import com.quriogamethechance.quriogame.ui.home.HomeFragment
import jakarta.inject.Inject

class CharacterDetailsFragment : BaseDialogFragment<FragmentCharacterDetialsBinding>(),
    CharacterDetailsViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetialsBinding
        get() = FragmentCharacterDetialsBinding::inflate

    @Inject
    lateinit var characterDetailsPresenter: CharacterDetailsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        characterDetailsPresenter.view = this
        binding.root.visibility = View.VISIBLE
        initButtonText()
        receiveData()
        initButton()
    }


    private fun initButton() {
        binding.okButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment , inclusive = false)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.buy.root.setOnClickListener {
            val action =
                CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToBuyCharacterFragment2(
                    binding.characterName.text.toString()
                )
            findNavController().navigate(action)
        }
    }


    private fun initButtonText() {
        val includedView = binding.buy
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.buy)
    }

    private fun receiveData() {
        val args = CharacterDetailsFragmentArgs.fromBundle(requireArguments())
        characterDetailsPresenter.getCharacter(args.characterName)
    }

    override fun onGetCharacterSuccess(characters: Character) {
        characters.apply {
            binding.characterName.text = name
            binding.characterAge.text = age
            binding.characterDescription.text = description
            binding.characterImage.setImageResource(characterImage)
            binding.lockImage.visibility = if (isOpen) View.GONE else View.VISIBLE
            binding.buy.root.visibility = if (isOpen) View.GONE else View.VISIBLE
        }
        initOkButton(characters)
    }

    override fun onSetCharacterSuccess() {
        val result = Bundle().apply {}
        parentFragmentManager.setFragmentResult(HomeFragment.Constant.UPDATE_CHARACTER_INFORMATION_KEY, result)

        findNavController().popBackStack(R.id.homeFragment,false)

    }

    private fun initOkButton(character: Character) {
        if(character.isOpen){
            binding.okButton.setOnClickListener {
                characterDetailsPresenter.setCharacter( character)
            }
        }else{
            binding.okButton.setOnClickListener {
                findNavController().popBackStack(R.id.homeFragment,false)
            }
        }


    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }
}