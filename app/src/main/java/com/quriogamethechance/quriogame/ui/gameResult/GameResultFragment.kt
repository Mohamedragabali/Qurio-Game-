package com.quriogamethechance.quriogame.ui.gameResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentGameResultBinding
import com.quriogamethechance.quriogame.presenter.gameResult.GameResultPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GameResultFragment : BaseFragment<FragmentGameResultBinding>() , GameResultViewInterface {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameResultBinding
        get() = FragmentGameResultBinding::inflate

    lateinit var gameDifficulty: String
    var gameId: Int = 0

    @Inject
    lateinit var gameResultPresenter: GameResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }

    override fun setup() {
        gameResultPresenter.view = this
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
        var coinsCount : Int
        if(starCount == 0){
             coinsCount = initialLoseViews(correctAnswerCount,bouns)

        }else{
            coinsCount =  initialWinViews(correctAnswerCount,bouns)
        }
        val simpleFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val currentDate = simpleFormat.format(Date())
        gameResultPresenter.insertLastGame(
            typeId = gameId,
            coinsCount = coinsCount,
            starCount = starCount,
            time = 0,
            date = currentDate
        )
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

    private fun initialLoseViews(correctAnswerCount: Int,bouns : Int ):Int {
        val coinsCount = correctAnswerCount * bouns
        binding.resultText.setImageResource(R.drawable.lose_text)
        binding.resultImage.setImageResource(R.drawable.lose_image)
        binding.coinsCountText.text = coinsCount.toString()
        binding.shareWithFriendButton.buttonText.text =
            getString(R.string.share_disappointment_with_friends)
        return coinsCount
    }

    private fun initialWinViews(correctAnswerCount: Int,bouns : Int) : Int {
        val coinsCount = correctAnswerCount * 100  * bouns
        binding.resultText.setImageResource(R.drawable.win_text)
        binding.resultImage.setImageResource(R.drawable.win_image)
        binding.coinsCountText.text = (correctAnswerCount * 100  * bouns).toString()
        binding.shareWithFriendButton.buttonText.text = getString(R.string.share_win_with_friends)
        return coinsCount
    }

    private fun initialButton() {
        binding.shareWithFriendButton.shareIcon.visibility = View.VISIBLE
        binding.playAgin.buttonText.text = getString(R.string.play_again)
        binding.backToHome.setOnClickListener {
            binding.root.findNavController().popBackStack()
        }
        binding.playAgin.root.setOnClickListener {
            val action = GameResultFragmentDirections.actionGameResultFragmentToGameFragment(
                gameId = gameId , gameDifficultyLevel = gameDifficulty)
            binding.root.findNavController().navigate(action)
        }
    }

    override fun insertLastGame(
        typeId: Int,
        coinsCount: Int,
        starCount: Int,
        time: Int,
        date: String
    ) {

    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }

}