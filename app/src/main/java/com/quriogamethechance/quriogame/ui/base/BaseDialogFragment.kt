package com.quriogamethechance.quriogame.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.quriogamethechance.quriogame.R


abstract class BaseDialogFragment<VB: ViewBinding>() : DialogFragment() {

    override fun getTheme(): Int = R.style.CenteredDialogTheme
    abstract val bindingInflater :(LayoutInflater, ViewGroup?, Boolean) -> VB
    private var _binding : ViewBinding? = null
    protected val binding : VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater,container,false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()

}