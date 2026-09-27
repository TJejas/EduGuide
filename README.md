# EduSelect

An on-device Android quiz app that measures a Class 11/12 student's interest across four career domains (Filmmaking, Hotel Management, Defence, Mass Communication), then combines it with the student's background to suggest realistic pathways.

Built with Kotlin and Jetpack Compose (Material 3), MVVM and Navigation Compose.

## Status

| Phase | Topic | Status |
|---|---|---|
| 0 | Environment and Compose fundamentals | Done |
| 1 | Project skeleton and navigation | In progress: 5 placeholder screens and NavHost done, `QuizViewModel` next |
| 2 to 9 | Data models, quiz engine, scoring, background info, recommendations, Defence deep-dive, persistence, pilot | Planned |

The full phase-by-phase plan is in [docs/ROADMAP.md](docs/ROADMAP.md).

## Project structure

```
app/src/main/java/com/example/eduselect/
├── MainActivity.kt      Hosts the app inside the theme and Scaffold
├── AppNavHost.kt        Navigation map: route → screen, button → next route
├── Screen.kt            Sealed class listing every route
└── ui/
    ├── screens/         Welcome, Quiz, DefenceFollowUp, BackgroundInfo, Results
    └── theme/           Material 3 colors, typography, theme
```

## Running

Open the project in Android Studio and press Run, or build from the command line:

```
./gradlew assembleDebug
```
