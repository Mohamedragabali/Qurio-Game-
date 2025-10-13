package com.quriogamethechance.quriogame.ui.game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentGameBinding
import com.quriogamethechance.quriogame.presenter.game.GamePresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseFragment
import jakarta.inject.Inject


class GameFragment : BaseFragment<FragmentGameBinding>() , GameViewInterface{

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameBinding
        get() = FragmentGameBinding::inflate

    @Inject
    lateinit var gamePresenter: GamePresenter
    private var mainButtonType = MainButtonType.CHECK
    private var correctAnswerCount = 0
    private var wrongAnswerCount = 0
    private var skipQuestionCount = 0
    private var questionNumber = 0
    private val questionNumberCount
        get() = questionNumber + 1
    lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        gamePresenter.view = this
        val args = GameFragmentArgs.fromBundle(requireArguments())
        val difficulty = args.gameDifficultyLevel
        val gamId = args.gameId
        gamePresenter.onGetQuestionGame(gameId =  gamId , difficulty = difficulty)
    }


    private fun initialBackButton() {
        binding.header.backButton.setOnClickListener {
            binding.root.findNavController().popBackStack()
        }
    }

    private fun initialNumberQuestion() {
        val questionNumber = "Q $questionNumberCount" + "/" + questions.size
        binding.questionNumber.text = questionNumber
    }

    private fun initialSkipButton() {
        binding.skipButton.setOnClickListener {
            getOptionsButton().forEach {
                it.isSelected = false
            }
            skipQuestionCount += 1
            checkAnswer(true)
        }
    }

    private fun initialMainButton() {
        binding.mainButton.root.setOnClickListener {
            when (mainButtonType) {
                MainButtonType.CHECK -> {
                    checkAnswer()
                }

                MainButtonType.NEXT -> {
                    nextQuestion()
                    makeResultIconInVisible()
                    initialNumberQuestion()
                }

                MainButtonType.FINISH -> {
                    Toast.makeText(
                        requireContext(),
                        "Co: $correctAnswerCount" +
                                "  Wr: $wrongAnswerCount" +
                                "  Sk: $skipQuestionCount", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun nextQuestion() {
        getOptionsButton().forEach {
            it.isClickable = true
        }
        questionNumber += 1
        mainButtonType = MainButtonType.CHECK
        initialMainButtonText()
        initialQuestion()
        makeSkipButtonVisible()
    }

    private fun checkAnswer(isSkip: Boolean = false) {
        var isSelectButton = isSkip
        getOptionsButton().forEach {
            isSelectButton = it.isSelected || isSelectButton
        }

        if (isSelectButton) {
            mainButtonType = if (questionNumberCount == questions.size) {
                MainButtonType.FINISH
            } else {
                MainButtonType.NEXT
            }
            initialMainButtonText()
            checkSelectedOption()
            makeSkipButtonInVisible()
        } else {
            Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show()

        }

    }

    private fun checkSelectedOption() {
        val options = getOptionsButton()
        val correctAnswer = questions[questionNumber].correctAnswer
        options.forEach {
            it.isClickable = false
            if (it.text == correctAnswer) {
                it.setBackgroundResource(R.drawable.correct_answer_button)
            }
            if (it.isSelected) {
                if (it.text == correctAnswer) {
                    initialViewOfResult(it, true)
                    correctAnswerCount += 1
                    it.setBackgroundResource(R.drawable.correct_answer_button)
                } else {
                    initialViewOfResult(it, false)
                    wrongAnswerCount += 1
                    it.setBackgroundResource(R.drawable.wrong_answer_button)
                }
            }
        }
    }

    private fun initialClickOnOption() {
        binding.apply {
            option1.setOnClickListener {
                selectOption(it)
            }
            option2.setOnClickListener {
                selectOption(it)
            }
            option3.setOnClickListener {
                selectOption(it)
            }
            option4.setOnClickListener {
                selectOption(it)
            }
        }
    }

    private fun selectOption(button: View) {
        getOptionsButton().forEach {
            if (it == button) {
                it.setBackgroundResource(R.drawable.selected_answer_button)
                it.isSelected = true
            } else {
                it.setBackgroundResource(R.drawable.out_line_button)
                it.isSelected = false
            }
        }
    }

    private fun makeSkipButtonVisible() {
        binding.skipButton.visibility = View.VISIBLE
    }

    private fun makeSkipButtonInVisible() {
        binding.skipButton.visibility = View.GONE
    }

    private fun initialQuestion() {
        getOptionsButton().forEach {
            it.setBackgroundResource(R.drawable.out_line_button)
            it.isSelected = false
        }
        binding.questionText.text = questions[questionNumber].question
        questions[questionNumber].options.shuffled().apply {
            binding.option1.text = this[0]
            binding.option2.text = this[1]
            binding.option3.text = this[2]
            binding.option4.text = this[3]
        }
    }

    private fun initialMainButtonText() {
        when (mainButtonType) {
            MainButtonType.CHECK -> {
                binding.mainButton.buttonText.text = getString(R.string.check)
            }

            MainButtonType.NEXT -> {
                binding.mainButton.buttonText.text = getString(R.string.next)
            }

            MainButtonType.FINISH -> {
                binding.mainButton.buttonText.text = getString(R.string.finish)
            }
        }
    }

    private fun initialViewOfResult(view: View, isCorrect: Boolean) {
        getResultIconViews().forEach {
            if (isCorrect) {
                it.setImageResource(R.drawable.bonus_icon)
            } else {
                it.setImageResource(R.drawable.minus_icon)
            }
        }
        makeResultIconVisible()
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.questionContainer)
        constraintSet.connect(
            binding.bonusIcon1.id,
            ConstraintSet.TOP,
            view.id,
            ConstraintSet.TOP
        )
        constraintSet.applyTo(binding.questionContainer)
    }

    fun getResultIconViews() = listOf(
        binding.bonusIcon1,
        binding.bonusIcon2,
        binding.bonusIcon3,
        binding.bonusIcon4,
        binding.bonusIcon5,
        binding.bonusIcon6
    )

    private fun makeResultIconVisible() {
        getResultIconViews().forEach {
            it.visibility = View.VISIBLE
        }
    }

    private fun makeResultIconInVisible() {
        getResultIconViews().forEach {
            it.visibility = View.GONE
        }
    }

    private fun getOptionsButton() =
        listOf(binding.option1, binding.option2, binding.option3, binding.option4)

    override fun onGetGameQuestion(questionsList: List<Question>) {
        questions = questionsList
        initialMainButtonText()
        initialQuestion()
        initialClickOnOption()
        initialSkipButton()
        initialMainButton()
        initialNumberQuestion()
        initialBackButton()
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }


}