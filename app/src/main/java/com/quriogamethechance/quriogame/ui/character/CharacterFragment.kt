package com.quriogamethechance.quriogame.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import com.quriogamethechance.quriogame.databinding.FragmentCharacterBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment


class CharacterFragment : BaseDialogFragment<FragmentCharacterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterBinding
        get() = FragmentCharacterBinding::inflate

    override fun setup() {

    }
}
