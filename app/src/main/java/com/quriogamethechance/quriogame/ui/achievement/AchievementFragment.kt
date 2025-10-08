package com.quriogamethechance.quriogame.ui.achievement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.AchievementBinding
import com.quriogamethechance.quriogame.databinding.CharcterBinding
import com.quriogamethechance.quriogame.databinding.FragmentAchievementBinding
import com.quriogamethechance.quriogame.databinding.FragmentCharacterBinding
import com.quriogamethechance.quriogame.ui.BaseDialogFragment
import com.quriogamethechance.quriogame.ui.character.CharacterFragmentDirections


class AchievementFragment : BaseDialogFragment<FragmentAchievementBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAchievementBinding
        get() = FragmentAchievementBinding::inflate

    private val characterNames = listOf("Quiz Rookie","Streak Starter","Lucky Guess","Explorer",
        "Trivia Champ","Collector","Legend","Untouchable","Quick Thinker","Collector","Lucky Guess")
    private val characters = listOf(
        R.drawable.quiz_rookie to R.drawable.quiz_rookie_closed,
        R.drawable.streak_starter to R.drawable.streak_starter_closed,
        R.drawable.lucky_guess to R.drawable.lucky_guess_closed,
        R.drawable.explorer to R.drawable.explorer_closed,
        R.drawable.trivia_champ to R.drawable.trivia_champ_closed,
        R.drawable.collector to R.drawable.collector_closed,
        R.drawable.legend to R.drawable.legend_closed,
        R.drawable.untouchable to R.drawable.untouchable_closed,
        R.drawable.quick_thinker to R.drawable.quick_thinker_closed,
        R.drawable.collector2 to R.drawable.collector2_closed,
        R.drawable.lucky_guess2 to R.drawable.lucky_guess2_closed,
        )
    override fun setup() {
        initButton()
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


    private fun initCharacter() {
        characters.forEachIndexed {index,(_, closeImage)->
            val achievement = AchievementBinding.inflate(layoutInflater,binding.achievements,false)
            achievement.achievementImage.setImageResource(closeImage)
            achievement.achievementName.text = characterNames[index]
            achievement.root.setOnClickListener {
                onClickCharacter(achievement.achievementName.text.toString())
            }
            binding.achievements.addView(achievement.root)
        }

    }

    private fun onClickCharacter(achievementName: String){
       val action = AchievementFragmentDirections.actionAchievementFragmentToAchievementDetialFragment(
           achievementName,
           false
       )
        findNavController().navigate(action)
    }


}
