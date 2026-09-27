package com.example.eduselect

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eduselect.ui.screens.BackgroundInfoScreen
import com.example.eduselect.ui.screens.DefenceFollowUpScreen
import com.example.eduselect.ui.screens.QuizScreen
import com.example.eduselect.ui.screens.ResultsScreen
import com.example.eduselect.ui.screens.WelcomeScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // One shared instance for every screen; survives rotation
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        modifier = modifier
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(onStartClick = { navController.navigate(Screen.Quiz.route) })
        }
        composable(Screen.Quiz.route) {
            // Phase 7: only go to DefenceFollowUp when Defence scores high
            QuizScreen(onFinishClick = { navController.navigate(Screen.DefenceFollowUp.route) })
        }
        composable(Screen.DefenceFollowUp.route) {
            DefenceFollowUpScreen(onContinueClick = { navController.navigate(Screen.BackgroundInfo.route) })
        }
        composable(Screen.BackgroundInfo.route) {
            BackgroundInfoScreen(onSubmitClick = { navController.navigate(Screen.Results.route) })
        }
        composable(Screen.Results.route) {
            ResultsScreen(onRestartClick = {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(Screen.Welcome.route) { inclusive = true }
                }
            })
        }
    }
}
