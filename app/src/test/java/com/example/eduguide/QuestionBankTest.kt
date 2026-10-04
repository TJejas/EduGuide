package com.example.eduguide

import com.example.eduguide.model.Domain
import com.example.eduguide.model.QuestionBank
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionBankTest {

    private val questions = QuestionBank.questions

    @Test
    fun has80QuestionsInTotal() {
        assertEquals(80, questions.size)
    }

    @Test
    fun has20QuestionsPerDomain() {
        for (domain in Domain.entries) {
            val count = questions.count { it.domain == domain }
            assertEquals("Wrong count for $domain", 20, count)
        }
    }

    @Test
    fun allIdsAreUnique() {
        val uniqueIds = questions.map { it.id }.toSet()
        assertEquals(questions.size, uniqueIds.size)
    }

    // Debug helper: prints all questions to the Run panel so you can see them.
    // It checks nothing, so it stays commented out. Uncomment it to view the list.
    // @Test
    // fun printAllQuestions() {
    //     questions.forEach { println(it) }
    // }
}
