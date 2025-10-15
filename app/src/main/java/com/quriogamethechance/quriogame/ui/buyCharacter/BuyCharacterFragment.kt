package com.quriogamethechance.quriogame.ui.buyCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentBuyCharacterBinding
import com.quriogamethechance.quriogame.presenter.buyCharacter.BuyCharacterPresenter
import com.quriogamethechance.quriogame.presenter.charcter.Character
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import com.quriogamethechance.quriogame.ui.characterDetails.CharacterDetailsFragmentArgs
import com.quriogamethechance.quriogame.ui.home.HomeFragment
import jakarta.inject.Inject

class BuyCharacterFragment : BaseDialogFragment<FragmentBuyCharacterBinding>() ,BuyCharacterViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBuyCharacterBinding
        get() = FragmentBuyCharacterBinding::inflate

    var character: Character? = null
    @Inject
    lateinit var buyCharacterPresenter: BuyCharacterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        buyCharacterPresenter.view = this
        disableBuyButton()
        initButtonText()
        initButton()
        receiveData()
    }
    private fun receiveData() {
        val args = CharacterDetailsFragmentArgs.fromBundle(requireArguments())
        buyCharacterPresenter.getData(args.characterName)
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


    private fun priceToString(price: Int): String{
        return when {
            price < 1000 -> price.toString()
            price < 1000000 -> "${price / 1000}k"
            else -> "${price / 1000000}m"
        }
    }
    private fun priceToInt(price: String): Int{
        return when {
            price.contains("k") -> price.replace("k", "").toInt() * 1000
            price.contains("m") -> price.replace("m", "").toInt() * 100000
            else -> price.toInt()
        }
    }

    private fun enableBuyButton() {
        binding.buyButton.apply {
            root.isClickable = true
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            endShadow.visibility = View.VISIBLE
            bottomShadow.visibility = View.VISIBLE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.second_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_with_radias)
        }
        binding.buyButton.root.setOnClickListener {
            buyCharacterPresenter.buyCharacter(priceToInt(binding.price.text.toString()) , character!!)
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

    override fun onGetDataSuccess(
        character: Character,
        coinsCount: Int
    ) {
        this.character = character
        character.apply {
            binding.characterImage.setImageResource(characterImage)
            binding.price.text = priceToString(price)
        }

        if (coinsCount >=priceToInt(binding.price.text.toString())) {
            enableBuyButton()
        } else {
            disableBuyButton()
        }
    }

    override fun onBuyCharacterSuccess() {
        val result = Bundle().apply {}
        parentFragmentManager.setFragmentResult(HomeFragment.Constant.UPDATE_DASHBOARD_DATA_KEY, result)
        parentFragmentManager.setFragmentResult(HomeFragment.Constant.UPDATE_CHARACTER_INFORMATION_KEY, result)

        findNavController().popBackStack(R.id.homeFragment,false)
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }

}