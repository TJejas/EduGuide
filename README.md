# EduGuide

An on-device Android quiz app that measures a Class 11/12 student's interest across four career domains (Filmmaking, Hotel Management, Defence, Mass Communication), then combines it with the student's background to suggest realistic pathways.

Built with Kotlin and Jetpack Compose (Material 3), MVVM and Navigation Compose.

## Status

| Phase | Topic | Status |
|---|---|---|
| 0 | Environment and Compose fundamentals | Done |
| 1 | Project skeleton and navigation | Done: 5 placeholder screens, NavHost, shared `QuizViewModel`, rotation-safe |
| 2 | Data models: domains and the 80-question bank | Done: `Domain`/`Question` and Defence models, 80 draft questions + 15 Defence follow-ups, unit test (question text pending lead review) |
| 3 | Quiz engine: shuffle, one question at a time, engage/skip | Done: shuffled quiz, "I'm interested" / Skip / Skip all remaining, "12 / 80" progress bar, per-domain engagement counts, Start Over resets the quiz |
| 4 | Scoring engine: counts to interest percentages | Next |
| 5 to 9 | Background info, recommendations, Defence deep-dive, persistence, pilot | Planned |

The full phase-by-phase plan is in [docs/ROADMAP.md](docs/ROADMAP.md).

## Project structure

```
app/src/main/java/com/example/eduguide/
├── MainActivity.kt      Hosts the app inside the theme and Scaffold
├── AppNavHost.kt        Navigation map: route → screen, button → next route
├── Screen.kt            Sealed class listing every route
├── QuizViewModel.kt     Quiz state shared by all screens: shuffled questions,
│                        current index, per-domain engagement counts (survives rotation)
├── model/
│   ├── Domain.kt                    The 4 career domains (enum)
│   ├── Question.kt                  One quiz question: id, domain, text
│   ├── DefenceBranch.kt             Air Force, Navy, Army (enum)
│   ├── DefenceFollowUpQuestion.kt   One Defence follow-up question
│   └── QuestionBank.kt              80 main + 15 Defence follow-up questions (draft text)
└── ui/
    ├── screens/         Welcome, Quiz, DefenceFollowUp, BackgroundInfo, Results
    └── theme/           Material 3 colors, typography, theme
```

## How the quiz works

1. The 80 questions are shuffled once when the quiz starts, so rotating the phone does not reshuffle them.
2. Each question shows "I'm interested" and "Skip". Only "I'm interested" adds 1 to that question's domain count.
3. "Skip all remaining" ends the quiz early and keeps the counts collected so far.
4. A "12 / 80" label and progress bar show how far the student is.
5. The Results screen shows the raw count per domain for now. Phase 4 turns these into percentages.
6. "Start Over" reshuffles the questions and clears the counts for the next student.

## Tests

Unit tests live in `app/src/test/` and run on the PC, not the emulator.

- `QuestionBankTest` checks the bank has 80 questions, 20 per domain, with unique ids.
- `QuizViewModelTest` runs full quizzes with engages and skips and checks the per-domain counts, Skip all, and Start Over.

## Running

Open the project in Android Studio and press Run, or build from the command line:

```
./gradlew assembleDebug
```

Run the unit tests:

```
./gradlew testDebugUnitTest
```
