package com.quriogamethechance.quriogame.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentSettingsBinding
import com.quriogamethechance.quriogame.presenter.setting.Setting
import com.quriogamethechance.quriogame.presenter.setting.SettingPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import jakarta.inject.Inject

class SettingsFragment : BaseDialogFragment<FragmentSettingsBinding>() , SettingViewInterface{
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding
        get() = FragmentSettingsBinding::inflate

    @Inject
    lateinit var settingPresenter: SettingPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        settingPresenter.view = this
        settingPresenter.getSetting()
        initButton()
        initButtonText()
        initSlider()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSlider() {
        binding.soundSlider.post {
            val maxDrag = binding.soundSlider.width - binding.soundSliderHandler.width
            var dX = 0f
            binding.soundSliderHandler.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX= v.x - event.rawX
                        true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        var newX = event.rawX + dX
                        newX= newX.coerceIn(0f, maxDrag.toFloat())
                        v.x = newX
                        true
                    }

                    else -> false
                }
            }
        }
        binding.musicSlider.post {
            val maxDrag = binding.musicSlider.width - binding.musicSliderHandler.width
            var dX = 0f
            binding.musicSliderHandler.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX= v.x - event.rawX
                        true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        var newX = event.rawX + dX
                        newX= newX.coerceIn(0f, maxDrag.toFloat())
                        v.x = newX
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun initButton() {
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.saveChanges.root.setOnClickListener {
            val soundDegree = binding.soundSliderHandler.x
            val musicDegree = binding.musicSliderHandler.x
            settingPresenter.setSetting(soundDegree, musicDegree)
        }
    }
    private fun initButtonText() {
        binding.saveChanges.buttonText.text = getString(R.string.save_changes)
    }

    override fun onGetSettingSuccess(setting: Setting) {
        binding.soundSliderHandler.x = setting.soundDegree
        binding.musicSliderHandler.x = setting.musicDegree
    }

    override fun onSetSettingSuccess() {
        findNavController().popBackStack()
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }

}