# EduSelect

An on-device Android quiz app that measures a Class 11/12 student's interest across four career domains (Filmmaking, Hotel Management, Defence, Mass Communication), then combines it with the student's background to suggest realistic pathways.

Built with Kotlin and Jetpack Compose (Material 3), MVVM and Navigation Compose.

## Status

| Phase | Topic | Status |
|---|---|---|
| 0 | Environment and Compose fundamentals | Done |
| 1 | Project skeleton and navigation | Done: 5 placeholder screens, NavHost, shared `QuizViewModel`, rotation-safe |
| 2 | Data models: domains and the 80-question bank | Next |
| 3 to 9 | Quiz engine, scoring, background info, recommendations, Defence deep-dive, persistence, pilot | Planned |

The full phase-by-phase plan is in [docs/ROADMAP.md](docs/ROADMAP.md).

## Project structure

```
app/src/main/java/com/example/eduselect/
├── MainActivity.kt      Hosts the app inside the theme and Scaffold
├── AppNavHost.kt        Navigation map: route → screen, button → next route
├── Screen.kt            Sealed class listing every route
├── QuizViewModel.kt     Shared state for all screens (survives rotation)
└── ui/
    ├── screens/         Welcome, Quiz, DefenceFollowUp, BackgroundInfo, Results
    └── theme/           Material 3 colors, typography, theme
```

## Running

Open the project in Android Studio and press Run, or build from the command line:

```
./gradlew assembleDebug
```
