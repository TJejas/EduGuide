package com.example.eduselect.model

object QuestionBank {

    const val QUESTIONS_PER_DOMAIN = 20

    val questions: List<Question> = Domain.entries.flatMap { domain ->
        List(QUESTIONS_PER_DOMAIN) { i ->
            Question(
                id = domain.ordinal * QUESTIONS_PER_DOMAIN + i + 1,
                domain = domain,
                text = "${domain.name} question ${i + 1}"
            )
        }
    }
}
