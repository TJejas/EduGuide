package com.example.eduguide

import com.example.eduguide.model.Domain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class QuizViewModelTest {

    private val viewModel = QuizViewModel()

    @Test
    fun countsStartAtZeroForEveryDomain() {
        assertEquals(Domain.entries.associateWith { 0 }, viewModel.engagementCounts)
    }

    @Test
    fun fullRunWithSkipsCountsOnlyEngagedQuestions() {
        // Engage on every other question, skip the rest, and track the expected counts by hand
        val expected = Domain.entries.associateWith { 0 }.toMutableMap()
        var engage = true
        while (viewModel.currentQuestion != null) {
            val question = viewModel.currentQuestion!!
            if (engage) {
                expected[question.domain] = expected.getValue(question.domain) + 1
                viewModel.onEngage()
            } else {
                viewModel.onSkip()
            }
            engage = !engage
        }
        assertEquals(expected, viewModel.engagementCounts)
        assertEquals(40, viewModel.engagementCounts.values.sum())
    }

    @Test
    fun skipAllEndsTheQuizAndKeepsCounts() {
        val domain = viewModel.currentQuestion!!.domain
        viewModel.onEngage()
        viewModel.onSkipAll()
        assertNull(viewModel.currentQuestion)
        assertEquals(1, viewModel.engagementCounts.getValue(domain))
    }

    @Test
    fun engageAfterTheLastQuestionDoesNothing() {
        viewModel.onSkipAll()
        viewModel.onEngage()
        assertEquals(0, viewModel.engagementCounts.values.sum())
    }

    @Test
    fun restartClearsCountsAndGoesBackToTheFirstQuestion() {
        viewModel.onEngage()
        viewModel.onSkipAll()
        viewModel.restart()
        assertEquals(0, viewModel.currentIndex)
        assertEquals(0, viewModel.engagementCounts.values.sum())
        assertEquals(80, viewModel.totalQuestions)
    }
}
