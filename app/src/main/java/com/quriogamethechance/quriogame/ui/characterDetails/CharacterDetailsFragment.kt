package com.quriogamethechance.quriogame.ui.characterDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentCharacterDetialsBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment

class CharacterDetailsFragment : BaseDialogFragment<FragmentCharacterDetialsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetialsBinding
        get() = FragmentCharacterDetialsBinding::inflate

    override fun setup() {

    }
}