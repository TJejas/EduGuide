package com.example.eduguide

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eduguide.model.Domain
import com.example.eduguide.model.Question
import com.example.eduguide.model.QuestionBank

class QuizViewModel : ViewModel() {

    // Shuffled once per quiz, so rotation doesn't reshuffle
    var questions: List<Question> by mutableStateOf(QuestionBank.questions.shuffled())
        private set

    var currentIndex by mutableIntStateOf(0)
        private set

    // Every domain starts at 0 so Phase 4 gets a score for all four, even with no taps
    var engagementCounts: Map<Domain, Int> by mutableStateOf(emptyCounts())
        private set

    // null once the student has gone past the last question
    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)

    val totalQuestions: Int
        get() = questions.size

    fun onEngage() {
        // Count the question on screen before moving past it
        val question = currentQuestion ?: return
        engagementCounts = engagementCounts + (question.domain to engagementCounts.getValue(question.domain) + 1)
        goToNext()
    }

    fun onSkip() {
        goToNext()
    }

    // Jumps straight to the "All done!" state; counts so far are kept
    fun onSkipAll() {
        currentIndex = questions.size
    }

    // Called by Start Over so the next student gets a fresh quiz
    fun restart() {
        questions = QuestionBank.questions.shuffled()
        currentIndex = 0
        engagementCounts = emptyCounts()
    }

    private fun goToNext() {
        if (currentIndex < questions.size) currentIndex++
    }

    private fun emptyCounts(): Map<Domain, Int> = Domain.entries.associateWith { 0 }
}
