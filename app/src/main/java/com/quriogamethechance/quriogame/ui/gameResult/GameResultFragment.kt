package com.quriogamethechance.quriogame.ui.gameResult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentGameResultBinding
import com.quriogamethechance.quriogame.ui.base.BaseFragment

class GameResultFragment : BaseFragment<FragmentGameResultBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameResultBinding
        get() = FragmentGameResultBinding::inflate

    lateinit var gameDifficulty: String
    var gameId: Int = 0

    override fun setup() {
        val args = GameResultFragmentArgs.fromBundle(requireArguments())
        val correctAnswerCount = args.correctAnswerCount
        val incorrectAnswerCount = args.incorrectAnswerCount
        val skippedQuestion = args.skippedAnswerCount
        val questionsCount = args.questionsCount
        gameDifficulty = args.gameDifficulty
        gameId = args.gameId

        initialButton()
        initialResultCounts(
            correctAnswerCount = correctAnswerCount,
            incorrectAnswerCount = incorrectAnswerCount,
            skippedQuestion = skippedQuestion
        )
        calculateResult(
            correctAnswerCount = correctAnswerCount,
            incorrectAnswerCount = incorrectAnswerCount,
            skippedQuestion = skippedQuestion,
            questionsCount = questionsCount,
            gameDifficulty = gameDifficulty
        )
    }

    private fun initialResultCounts(
        correctAnswerCount: Int,
        incorrectAnswerCount: Int,
        skippedQuestion: Int
    ) {
        binding.correctAnswerCount.text = correctAnswerCount.toString()
        binding.incorrectAnswerCount.text = incorrectAnswerCount.toString()
        binding.skippedAnswerCount.text = skippedQuestion.toString()
    }

    private fun calculateResult(
        correctAnswerCount: Int,
        incorrectAnswerCount: Int,
        skippedQuestion: Int,
        questionsCount: Int,
        gameDifficulty: String
    ) {
        val bouns = when (gameDifficulty) {
            "hard" -> {
                3
            }

            "Medium" -> {
                2
            }

            else -> {
                1
            }
        }
        val starCount = initialStars(correctAnswerCount,skippedQuestion, questionsCount)
        if(starCount == 0){
            initialLoseViews(correctAnswerCount,bouns)
        }else{
            initialWinViews(correctAnswerCount,bouns)
        }
    }

    private fun initialStars(
        correctAnswerCount: Int,
        skippedQuestion: Int,
        questionsCount: Int
    ) : Int {
        return if(correctAnswerCount == questionsCount){
            binding.firstStar.visibility = View.VISIBLE
            binding.midStar.visibility = View.VISIBLE
            binding.endStar.visibility = View.VISIBLE
            3
        }
        else if(correctAnswerCount >= questionsCount * 0.80 && skippedQuestion < 1 ){
            binding.firstStar.visibility = View.VISIBLE
            binding.endStar.visibility = View.VISIBLE
            2
        }
        else if (correctAnswerCount > questionsCount /2 ){
            binding.firstStar.visibility = View.VISIBLE
            1
        }
        else{
            0
        }
    }

    private fun initialLoseViews(correctAnswerCount: Int,bouns : Int ) {
        binding.resultText.setImageResource(R.drawable.lose_text)
        binding.resultImage.setImageResource(R.drawable.lose_image)
        binding.coinsCountText.text = (correctAnswerCount * bouns).toString()
        binding.shareWithFriendButton.buttonText.text = "Share disappointment with friends"
    }

    private fun initialWinViews(correctAnswerCount: Int,bouns : Int) {
        binding.resultText.setImageResource(R.drawable.win_text)
        binding.resultImage.setImageResource(R.drawable.win_image)
        binding.coinsCountText.text = (correctAnswerCount * 100  * bouns).toString()
        binding.shareWithFriendButton.buttonText.text = "Share win with friends"
    }

    private fun initialButton() {
        binding.shareWithFriendButton.shareIcon.visibility = View.VISIBLE
        binding.playAgin.buttonText.text = "Play again"
        binding.backToHome.setOnClickListener {
            binding.root.findNavController().popBackStack()
        }
    }

}