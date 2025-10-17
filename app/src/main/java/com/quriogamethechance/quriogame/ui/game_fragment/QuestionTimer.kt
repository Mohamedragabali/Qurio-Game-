package com.quriogamethechance.quriogame.ui.game_fragment

import android.os.CountDownTimer
import com.quriogamethechance.quriogame.ui.utils.Constants

class QuestionTimer(
    private val onFinishTimeQuestion: () -> Unit,
    private val onRunQuestionTime: (Long) -> Unit
) : CountDownTimer(
    Constants.QUESTION_TIME,
    Constants.COUNTDOWN_INTERVAL
) {
    private var _questionAnswerTime = 0
    val questionAnswerTime get() = _questionAnswerTime

    override fun onFinish() {
        onFinishTimeQuestion()
    }

    override fun onTick(p0: Long) {
        val timeInSecond : Long = p0 / 1000
        _questionAnswerTime = timeInSecond.toInt()
        onRunQuestionTime(timeInSecond)
    }
}