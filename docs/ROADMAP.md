# EduSelect: Android Learning Roadmap

*Plan written 2026-09-22. Progress: Phases 0 to 2 done (Phase 2 with placeholder question text), Phase 3 next.*

## What EduSelect Is

EduSelect is a self-administered Android quiz app that measures a Class 11/12 student's genuine interest across four career domains (Filmmaking, Hotel Management, Defence, and Mass Communication), then combines that interest score with the student's background (caste category, parents' profession, family financial status) to recommend realistic pathways, the way "high Defence interest + low income" points toward NDA because it's free. The pilot cohort is 10 South Indian volunteers spanning different religions and professions, hypothetically unable to afford much, used to validate that the quiz and recommendations hold up before a wider rollout.

## Confirmed Requirements

- Scoring is not right/wrong. A question is "answered" (engaged) or skipped; interest % per domain = how much a student engages with that domain's questions relative to the others. The top domain is reported, and the 2nd-best domain must also be shown.
- Students can skip any question they're not interested in, including skipping an entire domain.
- 4 domains, 20 questions each = 80 questions total, all shuffled together (not grouped by domain) so the student doesn't game the categorization.
- The quiz is entirely self-administered inside the app. No interviewer sits with the student.
- After the quiz, the app asks for caste/category, parents' profession, and family financial status, used only for the recommendation step (e.g. reservation-based suggestions, NDA vs. paid options), not for scoring.
- If Defence comes out as the top (or a strong) domain, a follow-up mini-assessment triggers to determine inclination among Air Force, Navy, and Army specifically. The other three domains stay single combined scores.
- Tech stack: Kotlin + Jetpack Compose, your call, confirmed with your lead.

## Tech Stack & Conventions

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** MVVM (ViewModel + State), standard for Compose apps
- **Navigation:** Jetpack Navigation Compose
- **Local storage:** Room, introduced in Phase 8, for the question bank and saved results
- **No backend for v1**. Everything runs on-device; a server sync can come later once the pilot validates the concept

## Learning Roadmap

### Phase 0: Environment & Fundamentals

**Goal:** get comfortable with Kotlin and Compose basics before touching EduSelect's actual logic.

1. Install Android Studio (latest stable), create an empty Compose project, run it on an emulator or your phone.
2. Learn the Kotlin you'll use immediately: `data class`, `enum class`, `sealed class`, `when`, nullable types (`?`), lambdas, `List`/`Map` operations (`filter`, `map`, `shuffled`).
3. Learn core Compose concepts: `@Composable` functions, `remember`/`mutableStateOf`, `Column`/`Row`/`LazyColumn`, `Button`/`Text`/`RadioButton`, state hoisting.
4. Build one throwaway screen (e.g. a counter or a 3-question mini quiz) to prove you can wire a button click to a state change and see the UI update.

**Ready for Phase 1 when:** you can build a screen with a button that changes what's on screen, without copy-pasting boilerplate you don't understand.

### Phase 1: Project Skeleton & Navigation

**Goal:** scaffold the real EduSelect app shell.

1. Create the EduSelect project (new package name, e.g. `com.yourname.eduselect`).
2. Set up Navigation Compose with placeholder screens: `WelcomeScreen`, `QuizScreen`, `DefenceFollowUpScreen`, `BackgroundInfoScreen`, `ResultsScreen`.
3. Wire simple navigation between them (Welcome → Quiz → Results) with dummy "Next" buttons, no real logic yet.
4. Set up a basic MVVM structure: one `QuizViewModel` the screens can read from, even if it's empty for now.

**Ready for Phase 2 when:** you can tap through all 5 screens in order and the app doesn't crash on rotation.

### Phase 2: Data Models

**Goal:** model the domains and the 80-question bank.

1. Define `enum class Domain { FILMMAKING, HOTEL_MANAGEMENT, DEFENCE, MASS_COMMUNICATION }`.
2. Define `data class Question(val id: Int, val domain: Domain, val text: String)`.
3. Write the 80 questions (20 per domain) as a static list for now, as plain Kotlin data, no database yet. Draft the actual question text with your lead, since question content needs their approval.
4. Define `data class DefenceFollowUpQuestion(val id: Int, val branch: DefenceBranch, val text: String)` with `enum class DefenceBranch { AIR_FORCE, NAVY, ARMY }` for Phase 7 later.

**Ready for Phase 3 when:** you can print/log the full question list and it's exactly 80 items, 20 per domain.

### Phase 3: Quiz Engine

**Goal:** the actual question flow: shuffle, show one at a time, allow skip.

1. In `QuizViewModel`, hold `allQuestions.shuffled()` and an index into it.
2. Render the current question in `QuizScreen` with an "engage" action (the student responds) and a "Skip" action.
3. Track responses in a `Map<Domain, Int>`. Increment the domain's count only when the student engages, not on skip.
4. Handle "skip all remaining" as an explicit action that jumps straight to submission.
5. Add a progress indicator (e.g. "12 / 80") so the student knows where they are.

**Ready for Phase 4 when:** you can complete a full run, skipping some questions, and end up with a correct per-domain engagement count.

### Phase 4: Scoring Engine

**Goal:** turn engagement counts into interest percentages.

1. Write a pure function `fun computeInterest(counts: Map<Domain, Int>): List<DomainScore>` returning each domain's % share of total engaged questions, sorted descending.
2. Confirm the exact formula with your lead. This roadmap assumes domain % = domain engagement ÷ total engagement across all domains. Flagged in Open Questions below.
3. Surface both the top domain and the 2nd-best domain distinctly on `ResultsScreen`, since the notes say both must be shown.
4. Unit test `computeInterest` with a few hand-picked count maps. It's a good first real unit test to write.

**Ready for Phase 5 when:** the results screen correctly shows 1st and 2nd domain with % for a full quiz run.

### Phase 5: Background Info Screen

**Goal:** collect caste/category, parents' profession, and family financial status after the quiz.

1. Build a simple form screen with a few fields/dropdowns. Keep it minimal and respectful; this is sensitive data.
2. Store it only in memory/local state for now, tied to that quiz session, with no network calls.
3. Flag for your lead: plaintext local storage of caste, religion, or financial data is sensitive even for a 10-person pilot. Ask whether it needs to be anonymized, encrypted, or dropped after generating the recommendation, before you build persistence in Phase 8.

**Ready for Phase 6 when:** you can capture background info and hand it to the next screen alongside the quiz scores.

### Phase 6: Recommendation Engine

**Goal:** combine interest scores and background info into a suggestion.

1. Write a function taking `(DomainScore list, category, financialStatus)` and returning a short list of suggested pathways, e.g. top domain Defence + low income → highlight NDA (free) over paid options.
2. Start with a simple rules table (domain × financial tier × category → suggestion text) rather than anything fancy; refine once you see real pilot responses.
3. This logic needs your lead's sign-off before it's shown to real students, because it's the part that actually influences a 17-year-old's career decision.

**Ready for Phase 7 when:** a test run through Defence + low-income background produces a sensible, lead-approved suggestion on screen.

### Phase 7: Defence Deep-Dive

**Goal:** when Defence is a strong result, ask follow-up questions to determine Air Force vs. Navy vs. Army inclination.

1. After scoring, check if Defence is the top domain (or above a threshold, to be confirmed with your lead).
2. If so, navigate to `DefenceFollowUpScreen` and run a smaller quiz using the `DefenceFollowUpQuestion` list from Phase 2, with the same engage/skip mechanic as the main quiz.
3. Score branch inclination the same way as Phase 4, reusing `computeInterest`-style logic, generalized to work on any domain-like enum.
4. Show the branch result alongside the main results.

**Ready for Phase 8 when:** a student who scores highest in Defence is automatically routed into the follow-up and gets an Air Force/Navy/Army result.

### Phase 8: Persistence & Results

**Goal:** save a completed run so it survives app restarts and can be reviewed later.

1. Add Room (or start simpler with DataStore/a JSON file) to persist one `QuizResult` record per completed run: scores, background info, recommendation, timestamp.
2. Build a simple "past results" view, useful for the pilot, where you'll want to look back at all 10 people's runs.
3. Revisit the Phase 5 sensitivity flag here and decide with your lead what actually gets written to disk.

**Ready for Phase 9 when:** you can close and reopen the app and still see a completed result.

### Phase 9: Pilot Testing & Iteration

**Goal:** run the 10-person cohort your lead described and use it to find real problems.

1. Recruit the 10 South Indian volunteers spanning different religions, professions, and (hypothetically) limited financial means, per the notes.
2. Have each person run the full app solo, exactly as a real student would, with no hand-holding, since that's the real test of the self-administered flow.
3. Log friction points: confusing questions, an unclear skip flow, weird recommendation output, crashes.
4. Bring findings back to your lead before changing any scoring weights, thresholds, or question text.

**Done when:** all 10 pilot runs complete without a crash and produce recommendations your lead considers reasonable.

## Open Questions 

- [ ] Exact interest % formula. This roadmap assumes % = domain engagement ÷ total engagement. Confirm this is what "checked based on more inclination" means, versus something rating-based.
- [ ] Threshold for triggering the Defence follow-up: strictly the top domain, or something softer (within some % of the top)?
- [ ] How sensitive background data (caste, financial status) should be stored: plaintext, encrypted, or discarded after generating the recommendation.
- [ ] Whether the recommendation engine's rules table needs sign-off before the pilot, or the pilot itself is meant to help define those rules.
