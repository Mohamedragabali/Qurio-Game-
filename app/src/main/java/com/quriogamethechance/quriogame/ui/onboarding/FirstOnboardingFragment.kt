package com.quriogamethechance.quriogame.ui.onboarding

import android.annotation.SuppressLint
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentFirstOnboardingBinding
import com.quriogamethechance.quriogame.ui.BaseFragment

class FirstOnboardingFragment : BaseFragment<FragmentFirstOnboardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFirstOnboardingBinding
        get() = FragmentFirstOnboardingBinding::inflate

    override fun setup() {
        initialButton()
        initialSwipeButton()
        initialTextColor()
    }

    private fun initialTextColor() {
        val positions = floatArrayOf(
            0.6f,
            1f
        )
        val textShader = LinearGradient(
            0f, 0f, 0f, binding.swipeToPlay.textSize,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.swipe_text_color),
                ContextCompat.getColor(requireContext(), R.color.primary)
            ),
            positions,
            Shader.TileMode.CLAMP
        )

        binding.swipeToPlay.paint.shader = textShader
        binding.swipeToPlay.invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initialSwipeButton() {
        binding.swipeContainer.post {
            val maxDrag =   binding.swipeContainer.height - binding.swipeHandle.height
            var dY = 0f

            binding.swipeHandle.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dY = v.y - event.rawY
                        true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        var newY = event.rawY + dY
                        newY = newY.coerceIn(0f, maxDrag.toFloat())
                        v.y = newY
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        if (v.y <= 50f) {
                           val action = FirstOnboardingFragmentDirections.actionFirstOnboardingFragmentToHomeFragment()
                            v.findNavController().navigate(action)
                        }
                        v.animate().y(maxDrag.toFloat()).setDuration(200).start()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initialButton() {
        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }
        binding.nextButton.setOnClickListener {
            val action = FirstOnboardingFragmentDirections.actionFirstOnboardingFragmentToSecondOnboardingFragment()
            it.findNavController().navigate(action)
        }
    }

}