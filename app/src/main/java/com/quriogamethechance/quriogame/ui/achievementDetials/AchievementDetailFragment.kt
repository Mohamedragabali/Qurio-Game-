package com.quriogamethechance.quriogame.ui.achievementDetials

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentAchievementDetialBinding
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment

class AchievementDetailFragment : BaseDialogFragment<FragmentAchievementDetialBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAchievementDetialBinding
        get() = FragmentAchievementDetialBinding::inflate

    private val achievementData = mapOf(
        "Quiz Rookie" to (R.drawable.quiz_rookie to R.drawable.quiz_rookie_closed),
        "Streak Starter" to (R.drawable.streak_starter to R.drawable.streak_starter_closed),
        "Lucky Guess" to (R.drawable.lucky_guess to R.drawable.lucky_guess_closed),
        "Explorer" to (R.drawable.explorer to R.drawable.explorer_closed),
        "Trivia Champ" to (R.drawable.trivia_champ to R.drawable.trivia_champ_closed),
        "Collector" to (R.drawable.collector to R.drawable.collector_closed),
        "Legend" to (R.drawable.legend to R.drawable.legend_closed),
        "Untouchable" to (R.drawable.untouchable to R.drawable.untouchable_closed),
        "Quick Thinker" to (R.drawable.quick_thinker to R.drawable.quick_thinker_closed),
        "Collector2" to (R.drawable.collector2 to R.drawable.collector2_closed),
        "Lucky Guess2" to (R.drawable.lucky_guess2 to R.drawable.lucky_guess2_closed)
    )

    override fun setup() {
        initButton()
        initButtonText()
        receiveData()
    }

    private fun receiveData() {
        val args = AchievementDetailFragmentArgs.fromBundle(requireArguments())
        initialDetails(args.achievementName, args.isOpenAchievement)
    }

    private fun initialDetails(achievementName: String, achievementIsOpened: Boolean) {
        val achievementDetail = achievementData[achievementName]
        achievementDetail?.apply {
            binding.achievementName.text = achievementName
            if(achievementIsOpened){
                binding.achievementImage.setImageResource(first)
                binding.achievementDescription.text = getString(R.string.achievement_description_open)
                binding.achievementBackgroundOpened.visibility = View.VISIBLE
                binding.shareWithFriendButton.root.visibility = View.VISIBLE
            }else{
                binding.achievementImage.setImageResource(second)
            }

        }


    }

    private fun initButton() {
        binding.shareWithFriendButton.shareIcon.visibility = View.VISIBLE
        binding.okButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
    }

    private fun initButtonText() {
        val includedView = binding.shareWithFriendButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.share_with_friends)
    }
}