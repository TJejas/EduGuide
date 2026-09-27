package com.example.eduselect
sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Quiz : Screen("quiz")
    data object DefenceFollowUp : Screen("defence_follow_up")
    data object BackgroundInfo : Screen("background_info")
    data object Results : Screen("results")
}

