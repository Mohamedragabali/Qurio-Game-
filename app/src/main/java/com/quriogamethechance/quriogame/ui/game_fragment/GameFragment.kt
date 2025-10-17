package com.quriogamethechance.quriogame.ui.game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
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
    private val questionTimer = QuestionTimer(
        onFinishTimeQuestion = ::onFinishTimeQuestion,
        onRunQuestionTime = ::onRunQuestionTime
    )
    private var mainButtonType = MainButtonType.NONE
    private var secondaryButtonType = SecondaryButtonType.SKIP
    private var correctAnswerCount = 0
    private var wrongAnswerCount = 0
    private var skipQuestionCount = 0
    private var questionNumber = 0
    private val questionNumberCount
        get() = questionNumber + 1
    lateinit var questions: List<Question>
    var isNextQuestion = false
    lateinit var difficulty : String
     var gamId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        gamePresenter.view = this
        gamePresenter.getLive()
        val args = GameFragmentArgs.fromBundle(requireArguments())
         difficulty = args.gameDifficultyLevel
         gamId = args.gameId
        getGameQuestions()
        handleSecondaryButton()
        disableMainButton()
        disAppearAllMainItem()

        initUpdateDataFromActionDialog()

    }
    fun initUpdateDataFromActionDialog(){
        parentFragmentManager.setFragmentResultListener( Constant.UPDATE_ALIVE_COUNT_KEY, this) { _, bundle ->
            gamePresenter.getLive()
        }

    }
    override fun onGetLiveSuccess(liveCount: Int) {
        binding.header.livesCount.text = liveCount.toString()
        if (liveCount == 0){
            disableMainButton()
            handleSecondaryButton()
        }else{
            questionTimer.start()
            enableMainButton()
            handleSkipButton()
        }
    }


    private fun enableMainButton() {
        mainButtonType = MainButtonType.CHECK
        binding.mainButton.apply {
            root.isClickable = true
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.on_primary))
            endShadow.visibility = View.VISIBLE
            bottomShadow.visibility = View.VISIBLE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.second_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_with_radias)
        }
    }

    private fun disableMainButton() {
        binding.mainButton.apply {
            root.isClickable = false
            buttonText.setTextColor(ContextCompat.getColor(requireContext(), R.color.shade_tertiary))
            endShadow.visibility = View.GONE
            bottomShadow.visibility = View.GONE
            secondColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
            topColor.background = ContextCompat.getDrawable(requireContext(), R.drawable.disable_rectangle_with_radias)
        }
    }


    private fun handleSkipButton() {
        binding.skipButton.text = requireContext().getString(R.string.skip)
        secondaryButtonType = SecondaryButtonType.SKIP
    }

    private fun handleSecondaryButton() {
        binding.skipButton.text = getString(R.string.buy_life)
        secondaryButtonType = SecondaryButtonType.BUY_LIFE

    }




    private fun getGameQuestions() {
        gamePresenter.onGetQuestionGame(gameId =  gamId , difficulty = difficulty)
        onLoading()
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
            questionTimer.cancel()
            if(secondaryButtonType == SecondaryButtonType.SKIP){
                getOptionsButton().forEach {
                    it.isSelected = false
                }
                skipQuestionCount += 1
                checkAnswer(true)
            }else{
                val action = GameFragmentDirections.actionGameFragmentToBuyLifeFragment()
                binding.root.findNavController().navigate(action)
            }
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
                    val action = GameFragmentDirections.actionGameFragmentToGameResultFragment(
                        correctAnswerCount = correctAnswerCount,
                        incorrectAnswerCount = wrongAnswerCount,
                        skippedAnswerCount = skipQuestionCount,
                        gameDifficulty = difficulty,
                        gameId = gamId,
                        questionsCount = questions.size
                    )
                    binding.root.findNavController().navigate(action)
                }

                MainButtonType.NONE -> {

                }
            }
        }
    }

    private fun nextQuestion() {
        isNextQuestion = true
        questionTimer.onFinish()
        getOptionsButton().forEach {
            it.isClickable = true
        }
        questionNumber += 1
        mainButtonType = MainButtonType.CHECK
        initialMainButtonText()
        initialQuestion()
        makeSkipButtonVisible()
        questionTimer.start()
    }

    private fun checkAnswer(isSkip: Boolean = false , isTimerEnded : Boolean = false) {
        var isSelectButton = isSkip  || isTimerEnded
        questionTimer.cancel()
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

            MainButtonType.NONE -> {
                binding.mainButton.buttonText.text = getString(R.string.check)
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
        onGetDataSuccess()
    }



    override fun onLoading() {
        disAppearAllMainItem()
        appearLoadingItems()
        disAppearErrorItems()
    }

    override fun onGetDataSuccess() {
        disAppearLoadingItems()
        disAppearErrorItems()
        appearAllMainItem()
    }

    override fun onGetDataError() {
        initialErrorButton()
        disAppearLoadingItems()
        appearErrorItems()
    }

    private fun initialErrorButton() {
        binding.tryAgianButton.buttonText.text = getString(R.string.try_again)
        binding.tryAgianButton.root.setOnClickListener {
            getGameQuestions()
        }
    }

    private fun disAppearErrorItems(){
        binding.noInternetImage.visibility = View.INVISIBLE
        binding.noInternetText.visibility = View.INVISIBLE
        binding.noInternetDescriptionText.visibility = View.INVISIBLE
        binding.tryAgianButton.root.visibility = View.INVISIBLE
    }
    private fun appearErrorItems(){
        binding.noInternetImage.visibility = View.VISIBLE
        binding.noInternetText.visibility = View.VISIBLE
        binding.noInternetDescriptionText.visibility = View.VISIBLE
        binding.tryAgianButton.root.visibility = View.VISIBLE
    }

    private fun disAppearLoadingItems(){
        binding.loadingAnimationIcon.visibility = View.INVISIBLE
        binding.loadingAnimationText.visibility = View.INVISIBLE
    }
    private fun appearLoadingItems(){
        binding.loadingAnimationIcon.visibility = View.VISIBLE
        binding.loadingAnimationText.visibility = View.VISIBLE
    }

    private fun disAppearAllMainItem(){
        binding.mainButton.root.visibility = View.INVISIBLE
        binding.header.root.visibility = View.INVISIBLE
        binding.questionText.visibility = View.INVISIBLE
        binding.questionNumber.visibility = View.INVISIBLE
        binding.skipButton.visibility = View.INVISIBLE
        binding.loadingText.visibility = View.INVISIBLE
        getOptionsButton().forEach {
            it.visibility = View.INVISIBLE
        }
        binding.headerContainer.visibility = View.INVISIBLE
        binding.loading.root.visibility = View.INVISIBLE
    }

    private fun appearAllMainItem(){
        binding.mainButton.root.visibility = View.VISIBLE
        binding.header.root.visibility = View.VISIBLE
        binding.questionText.visibility = View.VISIBLE
        binding.questionNumber.visibility = View.VISIBLE
        binding.skipButton.visibility = View.VISIBLE
        binding.loadingText.visibility = View.VISIBLE
        getOptionsButton().forEach {
            it.visibility = View.VISIBLE
        }
        binding.headerContainer.visibility = View.VISIBLE
        binding.loading.root.visibility = View.VISIBLE
    }



     fun onFinishTimeQuestion() {
         if(isNextQuestion){
             isNextQuestion = false
             binding.loadingText.text = "60 Sec"
         }else{
             checkAnswer(isTimerEnded = true)
             var isSelectButton = false
             getOptionsButton().forEach {
                 isSelectButton = it.isSelected || isSelectButton
             }
             if(!isSelectButton) {
                 skipQuestionCount++
             }
         }
     }

    fun onRunQuestionTime(currentTime: Long) {
        binding.loadingText.text = "$currentTime Sec"
    }
    object Constant{
        const val UPDATE_ALIVE_COUNT_KEY = "UPDATE_ALIVE_COUNT_KEY"
    }

}