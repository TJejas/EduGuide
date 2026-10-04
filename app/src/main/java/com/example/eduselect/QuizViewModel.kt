package com.example.eduselect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eduselect.model.Question
import com.example.eduselect.model.QuestionBank

class QuizViewModel : ViewModel() {

    // Shuffled once per quiz, so rotation doesn't reshuffle
    val questions: List<Question> = QuestionBank.questions.shuffled()

    var currentIndex by mutableIntStateOf(0)
        private set

    // null once the student has gone past the last question
    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)

    fun onEngage() {
        // Step 3: count this question's domain here
        goToNext()
    }

    fun onSkip() {
        goToNext()
    }

    private fun goToNext() {
        if (currentIndex < questions.size) currentIndex++
    }
}
